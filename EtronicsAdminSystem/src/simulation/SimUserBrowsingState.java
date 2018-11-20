/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author Brian
 */
public class SimUserBrowsingState implements ISimUserState{

    @Override
    public String makePurchase() {
        return "";
    }

    @Override
    public void addToCart(String userType) {
        // Browsing so nothing being added to cart
    }
    
}
