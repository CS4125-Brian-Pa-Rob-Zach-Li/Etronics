/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import administration.UIAdminController;
import administration.UIAdminModel;
import administration.gui.UIAdminView;
import customer.businesslogic.login;
import customer.gui.loginGUI;
import customer.gui.mainpageGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import products.BasicProduct;


/**
 *
 * @author XintingLi & Brian
 */
public class EtronicsSystem {

    /**
     * @param args the command line arguments
     */
    // Customer
    private static mainpageGUI mainPage;
    private static loginGUI loginPage;
    private static login loginBL;
    // Admin
    private static UIAdminController adminController;
    private static UIAdminModel adminModel;
    private static UIAdminView adminView;
    
    public static void main(String[] args) throws Exception {
        
        adminModel = new UIAdminModel();
        adminView = new UIAdminView(adminModel);
        adminController = new UIAdminController(adminView, adminModel);
        loginPage = new loginGUI();
        mainPage = new mainpageGUI();
        
        loginBL = new login();
        
        addListeners();
        loginPage.setVisible(true);
    } 
    
    public static void addListeners(){
        loginPage.addLoginListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String email = loginPage.getEmail();
                String password = loginPage.getPassword();
                String result = "";
                
                if(!loginBL.validateEmail(email))
                    JOptionPane.showMessageDialog(null, "Email invalid"); 
                else if(!loginBL.validatePW(password))
                    JOptionPane.showMessageDialog(null, "Password invalid"); 
                else{
                    try {
                        result = loginBL.getDetails(email, password);
                    } catch (SQLException ex) {
                        Logger.getLogger(loginGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, result); 
                }
                
                if(result.equals("Successfully")){
                    mainPage.setVisible(true);
                    loginPage.setVisible(false);
                }
            }});
    }
}
