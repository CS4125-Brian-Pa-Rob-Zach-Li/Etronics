/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import products.BasicProduct;
import products.Promotion;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public interface AdminDAO {
    //List<User> getAllUsers();
    ArrayList<BasicProduct> getAllProducts();
    ArrayList<Promotion> getAllPromotions();
    ArrayList<String> getAllCategories();
    //void insertUser(User u);
    boolean insertProduct(BasicProduct p);
    boolean insertPromotion(Promotion p);
    //void updateUser(User u);
    void updateProduct(BasicProduct p);
    //void deleteUser();
    boolean deleteProduct(int id);
}
