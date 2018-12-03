/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.gui;

/**
 *
 * @author zach
 */
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import database.ProductsDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductPanel extends JPanel implements ActionListener, PanelInterface{
    private String description;
    private int price;
    private int id;
    private String title;
    private JButton buyB;
    private JButton descrip;

    public ProductPanel(String desc, int prodPrice, String title, int id) 
    {
        this.draw(desc, prodPrice, title, id);
            
    }
    
   public ProductPanel(String desc, int prodPrice, String title, int id, int shippingCart) {
       GridLayout lay = new GridLayout(1,4);
            lay.setHgap(90);
            lay.setVgap(250);
            this.setLayout(lay);

            setPrice(prodPrice);
            setTitle(title);
            setID(id);

            buyB = new JButton("Remove");
            buyB.addActionListener(this);
            buyB.setSize(135, 40);
            descrip = new JButton("More Info");
            descrip.addActionListener(this);

            JLabel name = new JLabel(title);
            JLabel description = new JLabel(this.description);

            JLabel price = new JLabel("€"+Integer.toString(this.price));
            this.add(name);
            this.add(price);
            this.add(descrip);
            this.add(buyB);
            this.setSize(1000, 40);
            name.setSize(135,40);
            description.setSize(135,40);
            

            
            
   }

    public void setID(int id)
    {
        this.id = id;
    }
    
    public void setDescription(String desc) 
    {
            this.description = desc;
    }

    public void setPrice(int price)
    {
            this.price = price; 
    }

    public void setTitle(String title) 
    {
            this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton whichButton = (JButton)(e.getSource());
        String text = whichButton.getText();

        if(text.equals("More Info"))
        {
            JFrame descBox = new JFrame();
            descBox.setSize(300,300);
            JPanel blank = new JPanel();

            JLabel d = new JLabel(this.description);
            blank.add(d);
            descBox.add(blank);
            descBox.setVisible(true);
            descBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            buyB.setSize(135, 40);
        }

        if(text.equals("Add to Cart")) {
            ProductsDAO products = new ProductsDAO();
            try {
//                products.testCart("1",id);
                    products.updateShoppingCart(1, id, 1);
            } catch (SQLException ex) {
                Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(text.equals("Remove")) {
            ProductsDAO products = new ProductsDAO();
            try {
                products.removeFromShoppingCart(1,id);
            } catch (SQLException ex) {
                Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    @Override
    public void draw(String desc, int prodPrice, String title, int id) {
        GridLayout lay = new GridLayout(0,4);
            lay.setHgap(35);
            lay.setVgap(90);
            this.setLayout(lay);

            setDescription(desc);
            setPrice(prodPrice);
            setTitle(title);
            setID(id);


            buyB = new JButton("Add to Cart");
            buyB.addActionListener(this);
            descrip = new JButton("More Info");
            descrip.addActionListener(this);

            JLabel name = new JLabel(title);
            JLabel description = new JLabel(this.description);


            JLabel price = new JLabel("€"+Integer.toString(this.price));
            this.add(name);
            this.add(price);
            this.add(descrip);
            this.add(buyB);
            this.setBounds(7, 7, 1000, 60);
            this.setMaximumSize(new Dimension(1000, 451-14));
        
    }
}
