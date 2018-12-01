/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;
import database.ProductsDAO;

/**
 *
 * @author Brian
 */
public class SimUserShoppingState implements ISimUserState {

    private String details;
    private ProductsDAO pDAO;
    
    public SimUserShoppingState(ProductsDAO pDAO){
        details = "";
        this.pDAO = pDAO;
    }
    
    @Override
    public String makePurchase() {
        System.out.println("Make purchase.");
        return details;
    }

    @Override
    public void addToCart(String userType) {
        System.out.println("Item added to cart.");
        
        if(userType == "Person")
            details += "[item] ";
        else if(userType == "Company"){
            details += "[item]x10 ";
        }
    }
    
}
