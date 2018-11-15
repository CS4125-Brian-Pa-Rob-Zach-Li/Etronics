/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import database.AdminDAOImpl;
import database.AdminDAO;
import products.BasicProduct;
import products.Promotion;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Brian
 */
public class UIAdminModel {
    
    private ArrayList<BasicProduct> products;
    private ArrayList<String> categoryList;
    private DefaultListModel shopList;
    private AdminDAO dao;
    
    public UIAdminModel(){
        dao = new AdminDAOImpl();
        categoryList = new ArrayList();
        products = new ArrayList<BasicProduct>();
        updateProducts();
        updateCategories();
    }
    
    public ArrayList<BasicProduct> getAllBasicProducts(){
       return products;
    }
    
    public ArrayList<BasicProduct> getAllProductsByString(String s){
        ArrayList<BasicProduct> selectedProducts = new ArrayList<BasicProduct>();
        BasicProduct p = null;
        for(int x =0; x < products.size(); x++){
            p = products.get(x);
            if(p.getName().contains(s)){
                selectedProducts.add(p);
            }
        }
        
        return selectedProducts;
    }
    
    public boolean addProduct(BasicProduct p){
        return dao.insertProduct(p);
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
    
    public void addPromotion(Promotion p){
         dao.insertPromotion(p);
    }

    public DefaultListModel getShopList(){
        shopList = new DefaultListModel();
        return shopList;
    }
}
