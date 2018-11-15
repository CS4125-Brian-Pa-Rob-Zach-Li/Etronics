/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import database.AdminDAOImpl;
import database.AdminDAO;
import products.Product;
import products.Promotion;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Brian
 */
public class UIAdminModel {
    
    private ArrayList<Product> products;
    private ArrayList<String> categoryList;
    private DefaultListModel shopList;
    private AdminDAO dao;
    
    public UIAdminModel(){
        dao = new AdminDAOImpl();
        categoryList = new ArrayList();
        products = new ArrayList<Product>();
        updateProducts();
        updateCategories();
    }
    
    public ArrayList<Product> getAllProducts(){
       return products;
    }
    
    public void addProduct(Product p){
        dao.insertProduct(p);
    }
    
    public void addPromotion(Promotion p){
        dao.insertPromotion(p);
    }
    
    public ArrayList<String> getCategoryList(){
        return categoryList;
    }
    
    public void updateProducts(){
        products = dao.getAllProducts();
    }
    
    public void updateCategories(){
        categoryList = dao.getAllCategories();
    }

    public DefaultListModel getShopList(){
        shopList = new DefaultListModel();
        return shopList;
    }
}
