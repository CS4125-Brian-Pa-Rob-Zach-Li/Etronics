/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

/**
 *
 * @author Brian
 */
public class BasicProduct{
    
    protected int id;
    protected String name;
    protected String type;
    protected String description;
    protected int price;
    protected int promoID;
    
    public BasicProduct(){
        
    }
    
    // Used when creating a product from the GUI
    public BasicProduct(String name, int price, String description, String type){
        id = 0;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        promoID = 0;
    }
    
    // Used when a product is read from the Database
    public BasicProduct(int id, String name, int price, String description, String type){
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        promoID = 0;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int i){
        id = i;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String t){
        type = t;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String d){
        description = d;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int p){
        price = p;
    }

}
