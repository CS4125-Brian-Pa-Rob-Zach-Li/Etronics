/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import products.Product;
import administration.gui.UIAdminView;
import administration.UIAdminModel;
import administration.UIAdminController;
import database.AdminDAOImpl;
import database.AdminDAO;
import java.util.List;
import customer.gui.UICustomerView;

/**
 *
 * @author Brian
 */
public class EtronicsAdminSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        UIAdminModel model = new UIAdminModel();
        /*UIAdminView view = new UIAdminView(model);
        AdminDAO adminDAO = new AdminDAOImpl();
        UIAdminController controller = new UIAdminController(view, model);
        
        List<Product> prods = adminDAO.getAllProducts();
        
        int size = prods.size();
        int x = 0;
        while( x < size){
            Product p = prods.get(x);
            System.out.println(p.getID());
            System.out.println(p.getName());
            System.out.println(p.getType());
            System.out.println(p.getDescription());
            System.out.println(p.getPrice());
            x++;
        }*/
        UICustomerView view = new UICustomerView();
        
        view.setVisible(true);
    }
    
}
