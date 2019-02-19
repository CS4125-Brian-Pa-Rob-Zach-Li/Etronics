/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import administration.UIAdminController;
import database.ProductsDAO;

/**
 *
 * @author Brian
 */
public class SimUserStateFactory {
    private ProductsDAO pDAO = new ProductsDAO();
            
    public ISimUserState getUserState(String s){
        if(s.equals("Browsing"))
            return new SimUserBrowsingState();
        else if(s.equals("Shopping"))
            return new SimUserShoppingState(pDAO);
        else
            return null;
    }
}
