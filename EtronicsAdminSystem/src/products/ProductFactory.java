/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import products.Product;
import products.BasicProduct;

/**
 *
 * @author Brian
 */
public class ProductFactory {
    
    private Product p;
    
    public ProductFactory(){
        p = null;
    }
    
    public Product getProduct(int id, String name, int price, String description, String type, int promoID){
        
        if(promoID > 0)
            p = new PromotionalProduct(id, name, price, description, type, promoID);
        else
            p = new BasicProduct(id, name, price, description, type);
        return p;
    }
}
