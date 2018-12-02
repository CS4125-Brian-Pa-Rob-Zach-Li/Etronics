/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import database.ProductsDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @Patrick
 */
public class UICustomerModel {
    
    private ProductsDAO productsDAO;
    private ArrayList<String[]> productsArray;
    
   public UICustomerModel() {
       productsDAO = new ProductsDAO();
       productsArray = new ArrayList();
   }
   
   public ArrayList<String[]> getProducts() throws SQLException {
       productsArray = productsDAO.setProducts();
       return productsArray;
   }
   
   public ArrayList<String[]> getProducts(String type) throws SQLException {
       productsArray = productsDAO.setProducts(type);
       return productsArray;
   }
   
   public ArrayList<String[]> getCart(int userID) throws SQLException {
       productsArray = productsDAO.getCart(userID);
       return productsArray;
   }
   
   public ArrayList<String[]> searchProduct(String searchTerm) throws SQLException {
       productsArray = productsDAO.searchProducts(searchTerm);
       return productsArray;
   }
   
   public void createTransaction() throws SQLException {
       productsDAO.createTransaction(1, "Confirmed");
   }
   
    
}
