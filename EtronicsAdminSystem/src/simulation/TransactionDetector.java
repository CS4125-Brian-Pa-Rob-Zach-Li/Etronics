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
public class TransactionDetector implements ITransactionDetectionObserver{

    @Override
    public void transactionNotification(String details) {
        System.out.println("Transaction Details: "+details);
        // Add code here to update admin GUI with info of transaction
    }
    
}
