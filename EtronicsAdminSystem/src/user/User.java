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
public abstract class User {
    protected int userID;	
    protected String userName;	
    protected String userRole;	
    protected String userpw;
    protected String userEmail;
    
    public User(){
        userID = 0;
        userName = "";
        userRole = ""; 
        userpw = "";
        userEmail = "";       
    }
    
    public int getUserID() {
	return userID;
    }

    public void setUserID(int userID) {
	this.userID = userID;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
    	this.userName = userName;
    }

    public String getUserRole() {
	return userRole;
    }

    public void setUserRole() {
	this.userRole = "user";
    }
        
    public String getUserPW() {
	return userpw;
    }

    public void setUserPW(String userpw) {
    	this.userpw = userpw;
    }
        
    public String getUserEmail() {
	return userEmail;
    }

    public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
    }
        
}
