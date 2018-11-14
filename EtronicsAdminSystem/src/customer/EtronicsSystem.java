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
 * @author XintingLi
 */
public class EtronicsSystem {

    /**
     * @param args the command line arguments
     */
    private static mainpageGUI mainPage;
    private static loginGUI loginPage;
    public static void main(String[] args) throws Exception {
        
        //UIAdminModel model = new UIAdminModel();
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
        mainPage = new mainpageGUI();
        mainPage.setVisible(true);
    } 
}
