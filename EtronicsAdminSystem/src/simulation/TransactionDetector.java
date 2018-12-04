/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import administration.UIAdminController;

/**
 *
 * @author Brian
 */
public class TransactionDetector implements ITransactionDetectionObserver{

    private UIAdminController adminController;
    
    public TransactionDetector(UIAdminController ac){
        adminController = ac;
    }
    
    @Override
    public void transactionNotification(String details) {
        adminController.updateTransactionList(details);
    }
    
}
