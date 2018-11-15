/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.AdminDAO;
import products.BasicProduct;
import products.ProductFactory;
import products.Promotion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import administration.businesslogic.AdminBL;
import database.ConnectionHandler;

/**
 *
 * @author Brian
 */
public class AdminDAOImpl implements AdminDAO {
    
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement cStmt = null;
    private ResultSet resultSet = null;
    //private List<User> users;
    private ArrayList<BasicProduct> products = null;
    private ArrayList<String> cats = null;
    private ArrayList<Promotion> promos = null;
    private AdminBL abl;

    public AdminDAOImpl(){
        ConnectionHandler connHan = ConnectionHandler.getInstance();
        conn = connHan.makeConnection();
        abl = new AdminBL();
    }
    
    @Override
    public ArrayList<BasicProduct> getAllProducts(){
        products = new ArrayList<BasicProduct>();
        try{
            cStmt = conn.prepareCall("{call get_all_products()}");
            boolean result = cStmt.execute();
            
            if(result){    
                resultSet = cStmt.getResultSet();
            }
            else{
                products = null;
                return products;
            }
            
            BasicProduct prod = null;
            ProductFactory pf = new ProductFactory();
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
                System.out.println("");
                products.add(prod);
            }
        }catch(SQLException se){
            throw new RuntimeException("Error communicating with server.", se);
        }
        
        return products;
    }

    @Override
    public ArrayList<Promotion> getAllPromotions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<String> getAllCategories(){
        cats = new ArrayList<String>();
        try{
            cStmt = conn.prepareCall("{call get_all_categories()}");
            boolean result = cStmt.execute();
            
            if(result){    
                resultSet = cStmt.getResultSet();
            }
            else{
                cats = null;
                return cats;
            }
            
            String s = null;
            while(resultSet.next()){
                s = resultSet.getString("type");
                cats.add(s);
            }
        }catch(SQLException se){
            throw new RuntimeException("Error communicating with server.", se);
        }
        
        return cats;
    }

    @Override
    public boolean insertProduct(BasicProduct p){
        
        boolean valid = abl.validateAddProduct(p);
        
        if(valid){
            try{
                cStmt = conn.prepareCall("{call insert_product(?,?,?,?)}");
                cStmt.setString(1, p.getName());
                cStmt.setString(2, p.getType());
                cStmt.setString(3, p.getDescription());
                cStmt.setInt(4, p.getPrice());
                cStmt.execute();
            }catch(SQLException se){
                throw new RuntimeException("Error communicating with server.", se);
            }finally{
                try{
                    if(cStmt != null)
                        cStmt.close();
                }catch(SQLException se){
                    System.out.println("Error closing callable statement.");
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    @Override
    public boolean insertPromotion(Promotion p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateProduct(BasicProduct p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
