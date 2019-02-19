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
public class UserDecorator extends User{
    User user;
    boolean status = false;
    public UserDecorator(User user){
	this.user = user;
    }
    public void setStatus()
    {
        status = true;
    }
}
