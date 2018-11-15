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

public class ProductPanel extends JPanel implements ActionListener{
    private String description;
    private int price;
    private int id;
    private String title;

    public ProductPanel(String desc, int prodPrice, String title, int id) 
    {
            GridLayout lay = new GridLayout(0,5);
            lay.setHgap(35);
            this.setLayout(lay);

            setDescription(desc);
            setPrice(prodPrice);
            setTitle(title);
            setID(id);


            JButton buyB = new JButton("Add to Cart");
            buyB.addActionListener(this);
            JButton descrip = new JButton("More Info");

            descrip.addActionListener(this);

            JLabel name = new JLabel(title);
            JLabel description = new JLabel(this.description);


            JLabel price = new JLabel("€"+Integer.toString(this.price));
            this.add(name);
            this.add(price);
            this.add(descrip);
            this.add(buyB);
    }
    
   public ProductPanel(int prodPrice, String title, int id) {
       GridLayout lay = new GridLayout(0,5);
            lay.setHgap(90);
            lay.setVgap(250);
            this.setLayout(lay);

            setPrice(prodPrice);
            setTitle(title);
            setID(id);


            JButton buyB = new JButton("Remove");
            buyB.addActionListener(this);
            JButton descrip = new JButton("More Info");

            descrip.addActionListener(this);

            JLabel name = new JLabel(title);
            JLabel description = new JLabel(this.description);


            JLabel price = new JLabel("€"+Integer.toString(this.price));
            this.add(name);
            this.add(price);
            this.add(descrip);
            this.add(buyB);
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
        }

        if(text.equals("Add to Cart")) {
            ProductsDAO products = new ProductsDAO();
            try {
                products.testCart("1",id);
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
}
