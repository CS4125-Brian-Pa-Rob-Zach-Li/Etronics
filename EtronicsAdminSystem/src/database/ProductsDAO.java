package database;

import java.util.HashMap;
import database.ConnectionHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import products.BasicProduct;
import products.ProductFactory;

/**
 *
 * @author PatrickCruz
 */
public class ProductsDAO {

    private Statement statement = null;
    private CallableStatement cStmt = null;
    private ResultSet resultSet = null;
    private ArrayList<BasicProduct> allProducts;
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
        
        allProducts = new ArrayList<BasicProduct>();
        updateProductList();
    }
    
    public void updateProductList(){
        try{
            cStmt = conn.prepareCall("{call get_all_products()}");
            boolean result = cStmt.execute();
            
            if(result){    
                resultSet = cStmt.getResultSet();
            }
            else{
                allProducts = null;
                return;
            }
            
            BasicProduct prod = null;
            ProductFactory pf = new ProductFactory();
            allProducts.clear();
            
            while(resultSet.next()){
                int pID = Integer.parseInt(resultSet.getString("id"));
                String pName = resultSet.getString("name");
                String type = resultSet.getString("type");
                String desc = resultSet.getString("description");
                int price = Integer.parseInt(resultSet.getString("price"));
                int promoID;
                
                if(isInt(resultSet.getString("promoID"))){
                    promoID = Integer.parseInt(resultSet.getString("promoID"));
                }
                else
                    promoID = 0;
                
                prod = pf.getProduct(pID, pName, price, type, desc, promoID);
                allProducts.add(prod);
            }
        }catch(SQLException se){
            throw new RuntimeException("Error communicating with server.", se);
        }
    }
    
    public ArrayList<BasicProduct> getProductList(){
        updateProductList();
        return allProducts;
    }

    public void insertIntoShoppingCart(int userID, int itemID, int itemQuantity) throws SQLException {
        if(checkIfProductLastOne(itemID, itemQuantity)) {
            //Throw message not enough stock
            System.out.println("Not enough in stock");
        }
        else
            statement.executeUpdate("INSERT INTO shopping_carts(userID,productID,quantity)" +
                    " VALUES (" + userID + ", + " + itemID +", + " + itemQuantity + " )");
    }
    
    public void updateShoppingCart(int userID, int itemID, int itemQuantity) throws SQLException {
        
        resultSet = statement.executeQuery("SELECT COUNT(*) FROM shopping_carts WHERE userID ="+ userID+
                " AND productID = "+ itemID+ ";" );
        resultSet.next();
        if(resultSet.getInt(1) > 0) {
        resultSet = statement.executeQuery("SELECT quantity FROM shopping_carts WHERE userID ="+ userID+
                " AND productID = "+ itemID+ ";" );
        resultSet.next();
        int newQuantity = (resultSet.getInt("quantity")) + itemQuantity;
        statement.execute("UPDATE shopping_carts set quantity = " + newQuantity + " WHERE productID = " + itemID +
                " AND userID =" + userID);
        }
        
        else {       
            insertIntoShoppingCart(userID,itemID,itemQuantity);
        } 
            
    }

    private boolean checkIfProductLastOne(int itemID, int itemQuantity) throws SQLException {
        resultSet = statement.executeQuery("SELECT stock FROM etronics_products WHERE id = " + itemID + ";");
        if(resultSet.next() && (resultSet.getInt("stock") - itemQuantity) < 0 ) {
            return true;
        }
        else if(resultSet.next() && (resultSet.getInt("stock") - itemQuantity) > 0 ) {
          int newQuantity = (resultSet.getInt("stock") - itemQuantity);
            setStock(newQuantity,itemID);
            return false;
        }
        return false;
    }
    
    private void setStock(int newQuantity, int itemID) throws SQLException {
        statement.executeUpdate("UPDATE etronics_products SET stock = " + newQuantity + " WHERE id = " + itemID+";");
    }

    public void removeFromShoppingCart(int userID, int itemID) throws  SQLException {
        resultSet = statement.executeQuery("SELECT quantity FROM shopping_carts WHERE userID = " +
                userID + " AND productID = " + itemID + ";");
        resultSet.next();
        int removedStock = resultSet.getInt("quantity");
        
        resultSet = statement.executeQuery("SELECT stock FROM etronics_products WHERE id = "+ itemID + ";");
        resultSet.next();
        int currentStock = resultSet.getInt("stock");
        
        setStock(removedStock + currentStock, itemID);
        
        statement.executeUpdate("DELETE FROM shopping_carts WHERE userID = " +
        userID + " AND productID = " + itemID + ";");
    }

    void changeQuantityShoppingCart(int userID, int itemID, int newQuantity) throws SQLException {
            statement.executeUpdate("UPDATE shopping_carts SET quantity = " +
            newQuantity + " WHERE userID = " + userID + " AND itemID = " + itemID + ";");
    }

    public void createTransaction(int userID, String status) throws SQLException {
        StringBuilder description = new StringBuilder();
        HashMap<String,Integer> idQuantityHash = new HashMap<>();
        ArrayList<String[]> idArrayList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM shopping_carts WHERE userID = " + userID +";");

        String[] IDsArray;
        while (resultSet.next()) {
            IDsArray = new String[3];
            description.append(resultSet.getString("productID")).append("||");
            idQuantityHash.put(resultSet.getString("userID"),resultSet.getInt("quantity"));
            IDsArray[0] = resultSet.getString("userID");
            IDsArray[1] = resultSet.getString("quantity");
            IDsArray[2] = resultSet.getString("productID");
            idArrayList.add(IDsArray);
            
        }
        
        statement.executeUpdate("DELETE FROM shopping_carts WHERE userID="+userID+";");

        int totalCost = getTotalCost(idArrayList);
        statement.executeUpdate("INSERT INTO orders ( userID, description, totalCost, status)" +
                " VALUES (" + userID + ", '" + description.toString() + "', " +  totalCost + ", '" + status+ "' )");
  
    }

    public  int getTotalCost(ArrayList<String[]> userArray) throws SQLException {
        int totalCost = 0;
        HashMap<String,Integer> idPriceHash = new HashMap<>();
        ArrayList<String[]> idPriceArray = new ArrayList<>();
        
        resultSet = statement.executeQuery("SELECT id, price FROM etronics_products;");
        String[] idPrices;
        while (resultSet.next()) {
            idPrices = new String[2];
            idPriceHash.put(resultSet.getString("id"),resultSet.getInt("price"));
            idPrices[0] = resultSet.getString("id");
            idPrices[1] = resultSet.getString("price");
            idPriceArray.add(idPrices);
        }
        
        for(int i = 0; i < userArray.size(); i++) {
                for(int j = 0; j < idPriceArray.size(); j++) {
                     if(idPriceArray.get(j)[0].equals(userArray.get(i)[2])){
                         totalCost += Integer.parseInt(idPriceArray.get(j)[1]) * Integer.parseInt(userArray.get(i)[1]);
                     }
                }
        }
//        System.out.println("Total Cost: "+totalCost);
        
        return totalCost;
    }

    public ArrayList<String[]> setProducts() throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM etronics_products");
        ArrayList<String[]> productsArray = new ArrayList<>();
        String[] products;
        while(resultSet.next()) {
            products = new String[4];
            products[0] = resultSet.getString("name");
            products[1] = resultSet.getString("price");
            products[2] = resultSet.getString("id");
            products[3] = resultSet.getString("description");
            productsArray.add(products);
        }
        return productsArray;
    }
    
    public ArrayList<String[]> setProducts(String type) throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM etronics_products where type = '" + type + "';");
        ArrayList<String[]> productsArray = new ArrayList<>();
        String[] products;
        while(resultSet.next()) {
            products = new String[4];
            products[0] = resultSet.getString("name");
            products[1] = resultSet.getString("price");
            products[2] = resultSet.getString("id");
            products[3] = resultSet.getString("description");
            productsArray.add(products);
        }
        return productsArray;
    }
    
    //For testing the Database
    public void testCart(String userID, int productID) throws SQLException {
//        statement.executeUpdate("INSERT shopping_carts (userID, productID, quantity) VALUES(1,1,1)");
        System.out.println("INSERT shopping_carts (userID, productID, quantity) VALUES("+ userID+ "," +
                productID +",1)");
    }
    
    public ArrayList<String[]> searchProducts(String product) {
        ArrayList<String[]> productsArray = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM etronics_products WHERE name LIKE '%" + product + "%';");
            
            String[] products;
            while(resultSet.next()) {
                products = new String[4];
                products[0] = resultSet.getString("name");
                products[1] = resultSet.getString("price");
                products[2] = resultSet.getString("id");
                products[3] = resultSet.getString("description");
                productsArray.add(products);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productsArray;
    }
   
    
    public ArrayList<String[]> getCart(int userID) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM shopping_carts WHERE userID = " + userID + ";");
        System.out.println("SELECT * FROM shopping_carts WHERE userID = " + userID + ";");
        ArrayList<String> cartList = new ArrayList<>();
        ArrayList<String> quantityList = new ArrayList<>();
        ArrayList<String[]> shoppingCart = new ArrayList<>();
        
            while(resultSet.next()){
                cartList.add(resultSet.getString("productID"));
                quantityList.add(resultSet.getString("quantity"));
            }
            
            String[] products;
            for(int i = 0; i < cartList.size(); i++) {
                resultSet = statement.executeQuery("SELECT * FROM etronics_products WHERE id =" + cartList.get(i));
                products = new String[5];
                if(resultSet.next()){
                    resultSet.first();
                    products[0] = resultSet.getString("name");
                    products[1] = resultSet.getString("price");
                    products[2] = resultSet.getString("id");
                    products[3] = resultSet.getString("description");
                    products[4] = quantityList.get(i);
                    shoppingCart.add(products);
                }
            } 
        
        return shoppingCart;
    }

    public boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
}
