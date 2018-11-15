/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import administration.gui.UIAdminView;
import database.AdminDAO;
import products.BasicProduct;
import products.ProductFactory;
import products.Promotion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author Brian
 */
public class UIAdminController{
    
    private UIAdminView view;
    private UIAdminModel model;
    private AdminDAO dao;

    public UIAdminController(UIAdminView view, UIAdminModel model){
        this.view = view;
        this.model = model;
        
        addListeners();
        updateCategories();
    }
    
    public void addListeners(){
        // Add product button
        view.addNewProductListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int pPrice = 0;
                boolean result = isInt(view.getAddProductPrice());
                
                if(result){
                    String pName = view.getAddProductName();
                    pPrice = Integer.parseInt(view.getAddProductPrice());
                    String pDesc = view.getAddProductDesc();
                    String pCat = view.getAddProductCat();
                    if(!pName.equals("")  && !pDesc.equals("") && !pCat.equals("")){
                        ProductFactory pf = new ProductFactory();
                        BasicProduct p = pf.getProduct(0, pName, pPrice, pDesc, pCat, 0);
                        
                        boolean addSuccess = model.addProduct(p);
                        if(addSuccess){
                        model.updateProducts();
                        view.showInfoMessage("Product added successfully.");
                    }
                    else{
                            view.showErrorMessage("Invalid product!\n"
                                    + "Name must be less than 50 chars and "
                                    + "description must be less than 256 chars.");
                        }
                    }
                    else{
                        view.showErrorMessage("Fields left empty!");
                    }
                }
                else{
                    view.showErrorMessage("Invalid price supplied. Please use a whole real number.");
                }
                
            }});
        
        // Find product button
        view.addFindProductListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String textToSearch = view.getFindIDText();
                ArrayList<BasicProduct> selectedProducts = 
                        model.getAllProductsByString(textToSearch);
                
                ArrayList<String> prodNamesWithIDs = new ArrayList<String>();
                BasicProduct p = null;
                String s;
                for(int x = 0; x < selectedProducts.size(); x++){
                    p = selectedProducts.get(x);
                    s = p.getID()+": "+p.getName();
                    prodNamesWithIDs.add(s);
                }
                view.setFindIDResults(prodNamesWithIDs);
            }});
        
        
        view.addNewPromotionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String discount = view.getAddPromotionDiscountNum(), id = view.getAddPromotionProductID();
                String promoName = view.getAddPromotionPromoName(), date = view.getAddPromotionEndDate();
                boolean validInput;
                validInput = view.validateInput(discount,2);
                if(validInput) validInput = view.validateInput(id, 0);
                else if(validInput) validInput = view.validateDate(date);
                
                if(validInput)
                    {
                      int pID = Integer.parseInt(id);
                      int dis = Integer.parseInt(discount);
                      Date lastDate = new Date(date);
                      Promotion newPromotion = new Promotion(id, dis, lastDate);
                      UIAdminModel addPromotion = new UIAdminModel();
                      addPromotion.addPromotion(newPromotion);
                    } else {
                        view.resetAllTextBoxes();
                        JOptionPane.showMessageDialog(null,"Error: Input in incorrect format");
                        }
                    }
        });
    }
    
    public void updateCategories(){
        view.setCats(model.getCategoryList());
    }
    
    public boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
}
