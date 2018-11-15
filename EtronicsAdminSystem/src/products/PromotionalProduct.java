/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.util.Date;

/**
 *
 * @author Brian
 */
public class PromotionalProduct extends BasicProduct{
    
    private Promotion promo;
            
    public PromotionalProduct(){
        promo = null;
    }
    
    // Used when created after a database read
    public PromotionalProduct(int id, String name, int price, String description, String type, int promoID){
        super.id = id;
        super.name = name;
        super.price = price;
        super.description = description;
        super.type = type;
        this.promoID = promoID;
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
