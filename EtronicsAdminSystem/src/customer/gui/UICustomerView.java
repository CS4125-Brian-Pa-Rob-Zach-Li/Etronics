/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.gui;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import products.Product;

/**
 *
 * @author zach
 */
public class UICustomerView extends JFrame{
    
    public UICustomerView(){
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


        for(int i=0;i<80;i++)
        {
            ProductPanel example = new ProductPanel("description " +i,20,"Title "+i);
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
    }
}
