/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author XintingLi
 */
public class CustomerModel extends User{
    
    private String userAddress;
    private String userPN; //Phone Number
    private int cartID;

    public CustomerModel()
    {
        userID = 0;
        userName = "";
        userRole = "";
        userpw = "";
        userEmail = "";
        userAddress = "";
        userPN = "";
    }
       
    @Override
    public void setUserRole() {
	this.userRole = "custormer";
    }
        
        
    public String getUserAddress() {
	return userAddress;
    }

    public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
    }
         
    public String getUserPhoneNumber() {
	return userPN;
    }

    public void setUserPhoneNumber(String userPN) {
	this.userPN = userPN;
    }
    
    public void setUserCart(int cartID){
        this.cartID = cartID;
    }
    
    public int getUserCart() {
	return cartID;
    }
}
