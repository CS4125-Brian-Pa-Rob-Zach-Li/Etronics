package database;

import java.util.HashMap;
import database.ConnectionHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PatrickCruz
 */
public class ProductsDAO {

    private Statement statement = null;
    private ResultSet resultSet = null;
    ConnectionHandler connHand;
    Connection conn;

    public ProductsDAO(){
        try {
            connHand = ConnectionHandler.getInstance();
            conn = connHand.makeConnection();
            statement = conn.createStatement();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void insertIntoShoppingCart(int userID, int itemID, int itemQuantity) throws SQLException {
        if(checkIfProductLastOne(itemID, itemQuantity))
            //Throw message not enough stock
            System.out.println("Not enough in stock");
        
        else
            statement.executeUpdate("INSERT INTO shopping_carts(userID,itemID,quantity)" +
                    " VALUES (" + 1 + ", + " + 12 +", + " + 1+ " )");
    }

    private boolean checkIfProductLastOne(int itemID, int itemQuantity) throws SQLException {
        resultSet = statement.executeQuery("SELECT stock FROM store1 WHERE productID = " + itemID + ";");
        if((resultSet.getInt("stock") - itemQuantity) < 0 ) {
            return true;
        }
        else if((resultSet.getInt("stock") - itemQuantity) > 0 ) {
          int newQuantity = (resultSet.getInt("stock") - itemQuantity);
            setStock(newQuantity,itemID);
            return false;
        }
        return false;
    }
    
    private void setStock(int newQuantity, int itemID) throws SQLException {
        statement.executeUpdate("UPDATE store1 SET stock = " + newQuantity + " WHERE productID = " + itemID+";");
    }

    void removeFromShoppingCart(int userID, int itemID) throws  SQLException {
        resultSet = statement.executeQuery("SELECT quantity FROM shopping_carts WHERE userID = " +
                userID + " AND itemID = " + itemID + ";");
        int removedStock = resultSet.getInt("quantity");
        resultSet = statement.executeQuery("SELECT stock FROM store1 WHERE productID  = "+ itemID + ";");
        int currentStock = resultSet.getInt("stock");
        
        setStock(removedStock + currentStock, itemID);
        
        statement.executeUpdate("DELETE FROM shopping_carts WHERE userID = " +
        userID + " AND itemID = " + itemID + ";");
    }

    void changeQuantityShoppingCart(int userID, int itemID, int newQuantity) throws SQLException {
            statement.executeUpdate("UPDATE shopping_carts SET quantity = " +
            newQuantity + " WHERE userID = " + userID + " AND itemID = " + itemID + ";");
    }

    void createTransction(int userID, String status) throws SQLException {
        StringBuilder description = new StringBuilder();
        HashMap<String,Integer> idQuantityHash = new HashMap<>();
        resultSet = statement.executeQuery("SELECT * FROM shopping_carts WHERE userID = " + userID +";");

        while (resultSet.next()) {
            description.append(resultSet.getString("itemID")).append(",");
            idQuantityHash.put(resultSet.getString("itemID"),resultSet.getInt("quantity"));
        }
        int totalCost = getTotalCost(idQuantityHash);
        statement.executeUpdate("INSERT INTO orders ( userID, description, totalCost, status)" +
                " VALUES (" + userID + ", + " + description.toString() + ", + " +  totalCost + ", + " + status+ " )");
    }

    private int getTotalCost(HashMap<String,Integer> idQuantityHash) throws SQLException {
        int totalCost = 0;
        HashMap<String,Integer> idPriceHash = new HashMap<>();
        
        resultSet = statement.executeQuery("SELECT id, price FROM etronics_products;");
        while(resultSet.next()){
            idPriceHash.put(resultSet.getString("id"),resultSet.getInt("price"));
        }
        for( String key : idQuantityHash.keySet() ) {
            totalCost += idQuantityHash.get(key) * idPriceHash.get(key);
        }
        return totalCost;
    }

    public ArrayList<String[]> setProducts() throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM etronics_products");
        ArrayList<String[]> productsArray = new ArrayList<>();
        String[] products;
        while(resultSet.next()) {
            products = new String[3];
            products[0] = resultSet.getString("name");
            products[1] = resultSet.getString("price");
            products[2] = resultSet.getString("id");
            productsArray.add(products);
        }
        return productsArray;
    }
    
    //For testing the Database
    void testCart() throws SQLException {
        statement.executeUpdate("INSERT shopping_carts (userID, productID, quantity) VALUES(1,1,1)");
    }

}
