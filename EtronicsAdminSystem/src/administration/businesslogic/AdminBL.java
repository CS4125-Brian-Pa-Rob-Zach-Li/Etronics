/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration.businesslogic;

import products.BasicProduct;

/**
 *
 * @author Brian
 */
public class AdminBL {
    
    public AdminBL(){
    }
    
    public boolean validateAddProduct(BasicProduct p){
        
        if(!(isAlphaNumeric(p.getName())) || 
                !(isLessThanNChars(p.getName(), 50))){
            System.out.println("Here 1");
            return false;
        }
        else if(!(isLessThanNChars(p.getDescription(), 256))){
            System.out.println("Here 2");
            return false;
        }
        else
            return true;
    }
    
    public boolean isAlphaNumeric(String s){
        boolean isNotAlpha = s.matches("^.*[^a-zA-Z0-9 ].*$");
        
        return !isNotAlpha;
    }
    
    public boolean isLessThanNChars(String s, int n){
        if(s.length() < n)
            return true;
        else
            return false;
    }
}
