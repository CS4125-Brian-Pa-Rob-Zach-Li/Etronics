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
public class customerFactory extends userFactory{
        
    @Override
    User createuser() {
        return new customerModel();
    }
}
