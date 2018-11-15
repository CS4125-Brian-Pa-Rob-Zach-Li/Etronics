/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import products.BasicProduct;

/**
 *
 * @author Brian
 */
public class ProductFactory{
    
    
    public ProductFactory(){
    }
    
    public BasicProduct getProduct(int pID, String pName, int price, String type, String desc, int promoID){
        
        if(promoID > 0)
            return new PromotionalProduct(pID, pName, price, type, desc, promoID);
        else
            return new BasicProduct(pID, pName, price, type, desc);
    }

}
