/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import administration.UIAdminController;
import administration.UIAdminModel;
import administration.gui.UIAdminView;
import administration.businesslogic.UserManagement;
import customer.businesslogic.login;
import customer.businesslogic.register;
import customer.gui.loginGUI;
import customer.gui.mainpageGUI;
import customer.gui.registerGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import products.BasicProduct;
import administration.gui.UserManagementGUI;
import customer.gui.allProductGUI;
import customer.gui.myCartGUI;
import customer.gui.purchaseGUI;


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
    private static registerGUI registerPage;
    private static login loginBL;
    private static register registerBL;
    private static UICustomerModel custMod;
    private static allProductGUI allProdGUI;
    private static myCartGUI myCartGUI;
    private static purchaseGUI purchaseGUI;
    private static UICustomerController custCont;
    
    // Admin
    private static UIAdminController adminController;
    private static UIAdminModel adminModel;
    private static UIAdminView adminView;
    private static UserManagement UserManagementBL;
    private static UserManagementGUI UserManagementPage;
    
    
    public static void main(String[] args) throws Exception {
        
        //adminModel = new UIAdminModel();
        //adminView = new UIAdminView(adminModel);
        //adminController = new UIAdminController(adminView, adminModel);
        loginPage = new loginGUI();
        mainPage = new mainpageGUI();
        registerPage = new registerGUI();
        UserManagementPage = new UserManagementGUI();
        
        allProdGUI = new allProductGUI();
        myCartGUI = new myCartGUI();
        purchaseGUI = new purchaseGUI();
        custMod = new UICustomerModel();
        custCont = new UICustomerController(mainPage, loginPage, 
                myCartGUI, purchaseGUI, allProdGUI,custMod);
        
        UserManagementBL = new UserManagement();
        loginBL = new login();
        registerBL = new register();
        
        addloginListeners();
        addRegisterListeners_login();
        addRegisterListeners_register();
        //loginPage.setVisible(true
        mainPage.setVisible(true);
    } 
    
    public static void addloginListeners(){
        loginPage.addLoginListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String email = loginPage.getEmail();
                String password = loginPage.getPassword();
                String result = "";
                String rule = "";
                
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
                    try {
                        rule = loginBL.getRole(email);
                    } catch (SQLException ex) {
                        Logger.getLogger(EtronicsSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    loginPage.setVisible(false);
                    if(rule.equals("custormer")){
                        mainPage.setVisible(true);
                        try {
                            mainPage.showID(loginBL.setCustomerDetails(email));
                        } catch (SQLException ex) {
                            Logger.getLogger(EtronicsSystem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                        UserManagementPage.setVisible(true);
                }
            }});
    }
    
    public static void addRegisterListeners_login(){
        loginPage.addRegisterListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                registerPage.setVisible(true);
                loginPage.setVisible(false);
            }});
    }
    
    public static void addRegisterListeners_register(){
        registerPage.addRegisterListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String result = "";
                String uname = registerPage.getUserName();
                String password = registerPage.getPassword();
                String email = registerPage.getEmail();
                if(!registerBL.validateEmail(email))
                    JOptionPane.showMessageDialog(null, "Email invalud"); 
                else if(!registerBL.validatePW(password))
                    JOptionPane.showMessageDialog(null, "Password invalud"); 
                else{
                    result = registerBL.getDetails(uname, password, email);
                    JOptionPane.showMessageDialog(null, result); 
                }

                if(result.equals("Successfully")){
                    mainPage.setVisible(true);
                    try {
                        mainPage.showID(loginBL.setCustomerDetails(email));
                    } catch (SQLException ex) {
                        Logger.getLogger(EtronicsSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    registerPage.setVisible(false);
                }
            }});
    }
}
