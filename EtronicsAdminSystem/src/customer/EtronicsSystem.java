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
import customer.businesslogic.register;
import administration.gui.UserManagementGUI;
import customer.gui.allProductsGUI;
import customer.gui.loginGUI;
import customer.gui.mainpageGUI;
import customer.gui.myCartGUI;
import customer.gui.purchaseGUI;
import customer.gui.registerGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import simulation.*;


/**
 *
 * @author XintingLi & Brian AND a little bit from Patrick
 */
public class EtronicsSystem {

    /**
     * @param args the command line arguments
     */
    // Customer
    private static mainpageGUI mainPage;
    private static loginGUI loginPage;
    private static registerGUI registerPage;
    private static allProductsGUI productsPage;
    private static purchaseGUI purchasePage;
    private static myCartGUI cartPage;
    private static login loginBL;
    private static register registerBL;
    private static UICustomerController customerController;
    private static UICustomerModel customerModel;

    // Admin
    private static UIAdminController adminController;
    private static UIAdminModel adminModel;
    private static UIAdminView adminView;
    private static UserManagementGUI userManagementPage;
    
    
    public static void main(String[] args) throws Exception {
       
        adminModel = new UIAdminModel();
        adminView = new UIAdminView(adminModel);
        userManagementPage = new UserManagementGUI();
        adminController = new UIAdminController(adminView, adminModel, userManagementPage);

        loginPage = new loginGUI();
        mainPage = new mainpageGUI();
        registerPage = new registerGUI();
        productsPage = new allProductsGUI();
        purchasePage = new purchaseGUI();
        cartPage = new myCartGUI();
        
        loginBL = new login();
        registerBL = new register();
        
        customerModel = new UICustomerModel();
        
        customerController = new UICustomerController(mainPage, loginPage, 
            cartPage, purchasePage, productsPage, customerModel);
        
        //Start Simulation
        ControllerFactory cf = new ControllerFactory();
        Controller simController = cf.getSimulationController(adminController);
        new Thread(new Runnable() {
            @Override
            public void run() {
                simController.startSim();
            }
        }).start();
        
        addloginListeners();
        addRegisterListeners_login();
        addRegisterListeners_register();
        loginPage.setVisible(true);
        //mainPage.setVisible(true);
    } 
    
    public static void addloginListeners(){
        adminView.addLogoutListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                adminView.setVisible(false);
                loginPage.setVisible(true);
            }
        });
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
                        customerController.setUserID(loginBL.getUserID());
                        System.out.println(loginBL.getUserID());
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
                        adminView.setVisible(true);
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
                        customerController.setUserID(loginBL.getUserID());
                        System.out.println(loginBL.getUserID());
                    } catch (SQLException ex) {
                        Logger.getLogger(EtronicsSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    registerPage.setVisible(false);
                }
            }});
    }
}
