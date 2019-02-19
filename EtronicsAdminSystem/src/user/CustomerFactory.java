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
public class CustomerFactory extends UserFactory{
        
    @Override
    User createuser() {
        return new CustomerModel();
    }
}
