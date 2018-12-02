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
    public String makePurchase(int userID) {
        // User is in browsing state so nothing is being done
        return "";
    }

    @Override
    public void addToCart(String userType, int userId) {
        // Browsing so nothing being added to cart
    }
    
}
