/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import customer.gui.allProductsGUI;
import customer.gui.loginGUI;
import customer.gui.mainpageGUI;
import customer.gui.myCartGUI;
import customer.gui.purchaseGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class UICustomerController {
    
    private static mainpageGUI mainPage;
    private static loginGUI loginPage;
    private static myCartGUI cartPage;
    private static purchaseGUI purchasePage;
    private static allProductsGUI productPage;
    private UICustomerModel model;
    
    public UICustomerController(mainpageGUI mainPage, loginGUI loginPage, myCartGUI cartPage,
            purchaseGUI purchasePage, allProductsGUI productPage, UICustomerModel model) {
        
        UICustomerController.mainPage = mainPage;
        this.loginPage = loginPage;
        this.cartPage = cartPage;
        this.purchasePage = purchasePage;
        this.productPage = productPage;
        this.model = model;
        
        try {
            updateProductView();
            addListeners();
        } catch (SQLException ex) {
            Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProductView() throws SQLException {
//        mainPage
        mainPage.setProducts(model.getProducts("Television"), 1);
        mainPage.setProducts(model.getProducts("Oven"), 0);
        //Get userId for getCarts(userID)
        cartPage.setCart(model.getCart(1));
        //Get userID for getCarts
        purchasePage.setCart(model.getCart(1));
        productPage.setProducts(model.getProducts());
        
    }
    
    public void updateSearchView() throws SQLException {
        mainPage.setProducts(model.searchProduct(mainPage.getSearchText()),0);
        cartPage.setProducts(model.searchProduct(cartPage.getSearchText()));
        productPage.setProducts(model.searchProduct(productPage.getSearchText()));
    }
    
    public void addListeners() {
        mainPage.setSearchListener((ActionEvent e) -> {
            try {
                mainPage.refreshScreen();
                updateSearchView();
            } catch (SQLException ex) {
                Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        mainPage.setProductListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainPage.setVisible(false);
                productPage.setVisible(true);
            }
        });
        
        
        cartPage.setSearchListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    cartPage.refreshScreen();
                    updateSearchView();
                } catch (SQLException ex) {
                    Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cartPage.setHomeListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainPage.setVisible(true);
                cartPage.setVisible(false);
            }
        });
        
        cartPage.setPurchaseListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    purchasePage.refreshScreen();
                    updateProductView();
                } catch (SQLException ex) {
                    Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                purchasePage.setVisible(true);
                cartPage.setVisible(false);
            }
        });
        
        
        
        productPage.setSearchListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    productPage.refreshScreen();
                    updateSearchView();
                } catch (SQLException ex) {
                    Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        productPage.setHomeListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainPage.setVisible(true);
                productPage.setVisible(false);
                
            }
        });
        
        productPage.setCartListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    cartPage.refreshScreen();
                    updateProductView();
                } catch (SQLException ex) {
                    Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                cartPage.setVisible(true);
                productPage.setVisible(false);
                
            }
        });
                
        purchasePage.setPurchaseListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    model.createTransaction();
                } catch (SQLException ex) {
                    Logger.getLogger(UICustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Purchase made"); 
                mainPage.setVisible(true);
                purchasePage.setVisible(false);
            }
        });
        
        purchasePage.setCancelListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainPage.setVisible(true);
                purchasePage.setVisible(false);
            }
        });
    }
    
    
    public int callLogin()
    {
        int id = 0;
        loginPage = new loginGUI();
        mainPage.setVisible(false);
        loginPage.setVisible(true);
        //id = loginPage.getid();
        return id;
    }
    
    
}
