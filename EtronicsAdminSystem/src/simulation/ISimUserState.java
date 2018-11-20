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
public interface ISimUserState {
    
    public static final String STATE_BROWSING = "Browsing";
    public static final String STATE_SHOPPING = "Shopping";
    
    public String makePurchase();
    
    public void addToCart(String userType);

}
