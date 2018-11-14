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
public class BasicProduct implements Product{
    
    protected int id;
    protected String name;
    protected String type;
    protected String description;
    protected int price;
    
    public BasicProduct(){
        
    }
    
    public BasicProduct(String name, int price, String description, String type){
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }
    
    public BasicProduct(int id, String name, int price, String description, String type){
        
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }
    
    @Override
    public int getID(){
        return id;
    }
    
    public void setID(int i){
        id = i;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    public void setType(String t){
        type = t;
    }
    
    @Override
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String d){
        description = d;
    }
    
    @Override
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int p){
        price = p;
    }

}
