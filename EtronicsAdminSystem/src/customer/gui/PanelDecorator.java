/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.gui;

/**
 *
 * @author Zach
 */
public abstract class PanelDecorator implements PanelInterface {
    
    protected ProductPanel decoratedPanel;
    
    public PanelDecorator(ProductPanel decoratedPanel)
    {
        this.decoratedPanel = decoratedPanel;
    }

    @Override
    public void draw(String desc, int prodPrice, String title, int id) {
        decoratedPanel.draw(desc, prodPrice, title, id);
    }
    
}
