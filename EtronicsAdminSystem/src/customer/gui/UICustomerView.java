package customer.gui;

import database.ProductsDAO;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import products.BasicProduct;

/**
 *
 * @author zach
 */
public class UICustomerView extends JFrame{
    
    public UICustomerView(){
        try {
            this.setSize(1300,600);
            
            JPanel frame = new JPanel();
            frame.setLayout(new GridLayout(2,0));
            
            JPanel innerFrame4 = new JPanel();
            innerFrame4.setLayout(new GridLayout(2,0));
            
            //JButton part
            JButton cart = new JButton("Go to Checkout");
            JButton invisible = new JButton();
            invisible.setVisible(false);
            innerFrame4.add(invisible);
            /* -----------------------*/
            
            JPanel checkOutBorder = new JPanel();
            checkOutBorder.setBorder(new EmptyBorder(50,50,50,50));
            
            checkOutBorder.add(cart);
            innerFrame4.add(checkOutBorder);
            
            
            JPanel innerFrame = new JPanel();
            innerFrame.setLayout(new BoxLayout(innerFrame,BoxLayout.Y_AXIS));
            
            ProductsDAO productDAO = new ProductsDAO();
            
//            ArrayList<String[]> etronicsProductDetails = productDAO.setProducts();
            ArrayList<String[]> etronicsProductDetails = productDAO.getCart(1);
            
            for(int i=0;i< etronicsProductDetails.size();i++)
            {
                ProductPanel example = new ProductPanel(
                        String.valueOf(i+1),
                        Integer.parseInt(etronicsProductDetails.get(i)[1]),
                        etronicsProductDetails.get(i)[0], 
                        Integer.parseInt(etronicsProductDetails.get(i)[2]));
                innerFrame.add(example);
            }
            
            JScrollPane scroll = new JScrollPane(innerFrame);
            
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(50, 30, 300, 50);
            
            frame.add(scroll);
            
            frame.add(innerFrame4);
            
            this.add(frame);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(UICustomerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}