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
public class stuffModel extends User{
    public stuffModel()
    {
        userID = 0;
        userName = "";
        userRole = "";
        userpw = "";
        userEmail = "";
    }
       
    @Override
    public void setUserRole() {
	this.userRole = "stuff";
    }
        
}
