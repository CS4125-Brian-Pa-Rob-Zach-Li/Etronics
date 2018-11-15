/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import administration.gui.UIAdminView;
import database.AdminDAO;
import products.Product;
import products.ProductFactory;
import products.Promotion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        updateView();
    }
    
    public void addListeners(){
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
                        Product p = pf.getProduct(0, pName, pPrice, pDesc, pCat, 0);
                        model.addProduct(p);
                        model.updateProducts();
                        view.showInfoMessage("Product added successfully.");
                    }
                    else{
                        view.showErrorMessage("Fields left empty!");
                    }
                }
                else{
                    view.showErrorMessage("Invalid price supplied. Please use a whole real number.");
                }
                
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
    
    
    public void updateView(){
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
