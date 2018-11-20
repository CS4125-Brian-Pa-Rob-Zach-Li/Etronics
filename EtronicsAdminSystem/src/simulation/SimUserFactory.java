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
public class SimUserFactory {
    
    public SimUser createUser(String type, ISimUserState state){
        if(type.equals(ISimUser.COMPANY))
            return new SimUserCompany(state);
        else if(type.equals(ISimUser.PERSON))
            return new SimUserPerson(state);
        else
            return null;
    }
    
}
