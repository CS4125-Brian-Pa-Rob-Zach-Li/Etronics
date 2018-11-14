/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import customer.gui.loginGUI;
import customer.gui.mainpageGUI;

/**
 *
 * @author 
 */
public class UICustomerController {
    
    private static mainpageGUI mainPage;
    private static loginGUI loginPage;
    
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
