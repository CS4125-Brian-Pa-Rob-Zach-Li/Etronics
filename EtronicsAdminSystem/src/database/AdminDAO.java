/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import products.Product;
import products.Promotion;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public interface AdminDAO {
    //List<User> getAllUsers();
    ArrayList<Product> getAllProducts();
    ArrayList<Promotion> getAllPromotions();
    ArrayList<String> getAllCategories();
    //void insertUser(User u);
    void insertProduct(Product p);
    void insertPromotion(Promotion p);
    //void updateUser(User u);
    void updateProduct(Product p);
    //void deleteUser();
    void deleteProduct();
}
