/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import customer.businesslogic.login;
import customer.businesslogic.register;
import customer.gui.allProductsGUI;
import customer.gui.loginGUI;
import customer.gui.mainpageGUI;
import customer.gui.myCartGUI;
import customer.gui.purchaseGUI;
import customer.gui.registerGUI;

/**
 *
 * @author Windows 10
 */
public class test {
    
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
    
    public static void main(String [] args) {
        
           
        loginPage = new loginGUI();
        mainPage = new mainpageGUI();
        registerPage = new registerGUI();
        
        productsPage = new allProductsGUI();
        purchasePage = new purchaseGUI();
        cartPage = new myCartGUI();
        
        customerModel = new UICustomerModel();
        customerController = new UICustomerController(mainPage, loginPage, 
                cartPage, purchasePage, productsPage, customerModel);
        
        mainPage.setVisible(true);
        
    }
    
}
