/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.ArrayList;
import user.CustomerModel;

/**
 *
 * @author Brian
 */
public class SimUserCompany extends SimUser{
    
    private ArrayList<ITransactionDetectionObserver> observers;
    private ISimUserState state;
    private SimUserStateFactory simUserStateFact;
    private CustomerModel custModel;
    
    public SimUserCompany(ISimUserState state, SimUserStateFactory susf){
        this.state = state;
        simUserStateFact = susf;
        observers = new ArrayList<ITransactionDetectionObserver>();
    }
    
    @Override
    public void setCustomerModel(CustomerModel cm){
        custModel = cm;
    }
    
    @Override
    public ISimUserState getState() {
        return state;
    }
    
    @Override
    public void setState(ISimUserState s) {
        state = s;
    }

    @Override
    public void addToCart() {
        state.addToCart(ISimUser.COMPANY, custModel.getUserID());
    }

    @Override
    public void makePurchase() {
        String purchaseDetails = state.makePurchase(custModel.getUserID());
        if(!purchaseDetails.equals("")){
            notifyObservers(purchaseDetails);
            state = simUserStateFact.getUserState("Browsing");
        }
    }

    @Override
    public void registerObserver(ITransactionDetectionObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ITransactionDetectionObserver o) {
        if(observers.contains(o)){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers(String details) {
        for(int x = 0; x < observers.size(); x++)
            observers.get(x).transactionNotification(details);
    }

}
