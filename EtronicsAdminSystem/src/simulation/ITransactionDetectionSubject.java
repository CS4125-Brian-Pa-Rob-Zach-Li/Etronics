/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import user.User;

/**
 *
 * @author Brian
 */
public interface ITransactionDetectionSubject {
    
    public void registerObserver(ITransactionDetectionObserver o);
    
    public void removeObserver(ITransactionDetectionObserver o);
    
    public void notifyObservers(String details);

}
