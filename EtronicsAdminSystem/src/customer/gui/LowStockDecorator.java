/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.gui;

import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author Zach
 */
public class LowStockDecorator extends PanelDecorator {
    
    
    
    public LowStockDecorator(ProductPanel decoratedPanel)
    {
        super(decoratedPanel);
    }
    
    @Override
    public void draw(String desc, int prodPrice, String title, int id)
    {
        decoratedPanel.draw(desc,prodPrice,title,id);
        setYellowBorder(decoratedPanel);
    }
    
    private void setYellowBorder(ProductPanel decoratedPanel)
    {
        decoratedPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
    }
    
}
    

