/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import products.Product;
import java.util.Date;

/**
 *
 * @author Brian
 */
public class PromotionalProduct implements Product{
    
    private Promotion promo;
    private int id;
    private String name;
    private int price;
    private String description;
    private String type;
    private int promoID;
            
    public PromotionalProduct(){
        promo = null;
    }
    
    public PromotionalProduct(int id, String name, int price, String description, String type, int promoID){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.promoID = promoID;
    }
    
    public PromotionalProduct(String name, int price, String description, String type, int promoID){
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.promoID = promoID;
    }

    @Override
    public int getID() {
           return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }
    
    public int getPromoID(){
        return promo.getPromoID();
    }
    
    public Date getPromoEndDate(){
        return promo.getEndDate();
    }
    
    public String getPromoName(){
        return promo.getPromoName();
    }
    
    public int getPromoDiscount(){
        return promo.getDiscountAmount();
    }
    
    public void setPromo(Promotion p){
        promo = p;
    }
}
