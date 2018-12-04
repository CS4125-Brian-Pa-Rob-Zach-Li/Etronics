/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;
import database.ProductsDAO;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import products.BasicProduct;

/**
 *
 * @author Brian
 */
public class SimUserShoppingState implements ISimUserState {

    private String details;
    private ProductsDAO pDAO;
    private ArrayList<BasicProduct> products;
    
    public SimUserShoppingState(ProductsDAO pDAO){
        details = "";
        this.pDAO = pDAO;
    }
    
    @Override
    public String makePurchase(int userID) {
        details = "";
        try{
            ArrayList<String[]> cartDetails = pDAO.getCart(userID);
            pDAO.createTransaction(userID, "complete");
            
            int total = 0;
            
            if(cartDetails.size() > 0)
                details += "<html>-----Transaction Start-----<br>";
            
            for(int x = 0; x < cartDetails.size(); x++){
                details += "Product: " + cartDetails.get(x)[0] + " ";
                details += "| [Price: $" + cartDetails.get(x)[1] + "] x"+cartDetails.get(x)[4];
                total += Integer.parseInt(cartDetails.get(x)[1]) * Integer.parseInt(cartDetails.get(x)[4]);
                details += "<br>";
            }
            
            if(cartDetails.size() > 0){
                details += "<br>";
                details += "Total Cost: $"+total+"<br>";
                details += "-----Transaction End-----<br><br></html>";
            }
        }catch(Exception sqlEx){
            System.out.println("SimUserShoppingState.makePurchase: "+sqlEx);
        }
        return details;
    }

    @Override
    public void addToCart(String userType, int userID) {

        int productID = get_random_product();
        
        if(userType == "Person"){
            try{
                pDAO.insertIntoShoppingCart(userID, productID, 1);
            }catch(Exception sqlEx){
                System.out.println("SimUserShoppingState.addToCart: "+sqlEx);
            }
        }
        else if(userType == "Company"){
            try{
                pDAO.insertIntoShoppingCart(userID, productID, 10);
            }catch(Exception sqlEx){
                System.out.println("SimUserShoppingState.addToCart: "+sqlEx);
            }
        }
    }
    
    public int get_random_product(){
        int id = 0;
        products = pDAO.getProductList(); 
        id = ThreadLocalRandom.current().nextInt(0, (products.size()-1) + 1);
        return id;
    }
    
}
