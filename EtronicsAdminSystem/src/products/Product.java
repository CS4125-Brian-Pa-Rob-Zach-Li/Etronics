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
public interface Product {
    public int getID();

    public String getName();
 
    public String getType();
 
    public String getDescription();

    public int getPrice();
}
