/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import user.CustomerModel;

/**
 *
 * @author Brian
 */
public interface ISimUser {
    public static final String COMPANY = "Company";
    public static final String PERSON = "Person";
    
    public ISimUserState getState();
    
    public void setState(ISimUserState s);
    
    public void setCustomerModel(CustomerModel cm);
    
    public void addToCart();
    
    public void makePurchase();
}
