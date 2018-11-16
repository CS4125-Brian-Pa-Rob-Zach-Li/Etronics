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
public abstract class userFactory {
    abstract User createuser();
    
    public User getuser(){
        User user;
        user = createuser();
        return user;
    }
}
