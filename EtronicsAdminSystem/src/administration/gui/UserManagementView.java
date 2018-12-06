/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration.gui;

import administration.businesslogic.UserManagement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import user.CustomerModel;

/**
 *
 * @author XintingLi
 */
public class UserManagementView extends JPanel{
    
    private JPanel mainContent;
    private JLabel heading;
    //add
    private JLabel addUserTitleLabel;
    private JLabel userNameLabel;
    private JLabel userEmailLabel;
    private JLabel passwordLabel;
    private JTextField userNameField;
    private JTextField userEmailField;
    private JTextField userPasswordField;
    private JButton userAddButton;
    // Delete
    private JLabel delUserTitleLabel;
    private JLabel delUserEmailLabel;
    private JComboBox delEmailComboBox;
    private JButton userDelButton;
    // Change Product
    private JLabel changeUserTitleLabel;
    private JLabel changeUserEmaliLabel;
    private JLabel changeNewNameLabel;
    private JLabel changeNewEmailLabel;
    private JTextField newNameField;
    private JTextField newEmailField;
    private JComboBox changeEmailComboBox;
    private JButton changeUserButton;
    private JButton changeUserSelectedButton;
    // Add Category
    private JLabel resetUserTitleLabel;
    private JLabel resetUserEmailLabel;
    private JComboBox resetUserEmailComboBox;
    private JButton resetUserButton;
    
    private ArrayList<CustomerModel> list;
    int selectedChangeValue = 1;
    int selectedResetValue = 1;
    int selectedDeleteValue = 1;
    
    public UserManagementView() throws SQLException, ClassNotFoundException{
        list = new ArrayList<CustomerModel>();
        heading = new JLabel("Etronics Admin System", JLabel.CENTER);
        // Add Products
        addUserTitleLabel = new JLabel("Add User");
        userNameLabel = new JLabel("Name: ");
        userEmailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        userNameField = new JTextField();
        userEmailField = new JTextField();
        userPasswordField = new JTextField();
        userAddButton = new JButton("Add User");
        // Delete Products
        delUserTitleLabel = new JLabel("Delete User");
        delUserEmailLabel = new JLabel("User Email: ");
        delEmailComboBox = new JComboBox();
        userDelButton = new JButton("Delete User");
        // Change Product
        changeUserTitleLabel = new JLabel("Change Product");
        changeUserEmaliLabel = new JLabel("User Email: ");
        changeEmailComboBox = new JComboBox();
        changeUserSelectedButton = new JButton("Select");
        changeNewNameLabel = new JLabel("New Name: ");
        changeNewEmailLabel = new JLabel("New Email: ");
        newNameField = new JTextField();
        newEmailField = new JTextField();
        changeUserButton = new JButton("Change User");
        // Find product ID
        resetUserTitleLabel = new JLabel("Reset Password");
        resetUserEmailLabel = new JLabel("User Email: ");
        resetUserEmailComboBox = new JComboBox();
        resetUserButton = new JButton("Reset");
    
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        heading.setFont(new Font("",Font.PLAIN, 40));
        mainContent.add(heading, BorderLayout.NORTH);
        
        JPanel variablePanel = setupProductGUI();
        
        mainContent.add(variablePanel, BorderLayout.CENTER);
        this.add(mainContent);
//        this.setContentPane(mainContent);
//        this.setSize(900,600);
//        this.setResizable(false);
//        this.setTitle("Etronics Admin System");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public JPanel setupProductGUI(){
        
        JPanel variablePanel = new JPanel();
        //variablePanel.setLayout(new BoxLayout(variablePanel, BoxLayout.Y_AXIS));
        variablePanel.setLayout(new GridLayout(2,2,50,50));
        variablePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Add Product Panel
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new BoxLayout(addUserPanel, BoxLayout.Y_AXIS));
        addUserPanel.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        JPanel addUserTitlePanel = new JPanel(new GridLayout(0,1));
        addUserTitlePanel.add(addUserTitleLabel);
        
        JPanel addUserLabelPanel = new JPanel();
        addUserLabelPanel.setLayout(new GridLayout(0, 1));
        addUserLabelPanel.add(userNameLabel);
        addUserLabelPanel.add(userEmailLabel);
        addUserLabelPanel.add(passwordLabel);
        
        JPanel addUserToolPanel = new JPanel();
        addUserToolPanel.setLayout(new GridLayout(0, 1));
        addUserToolPanel.add(userNameField);
        addUserToolPanel.add(userEmailField);
        addUserToolPanel.add(userPasswordField);
        
        addUserPanel.add(addUserTitlePanel);
        
        JPanel yetAnotherPanel = new JPanel();
        yetAnotherPanel.setLayout(new BoxLayout(yetAnotherPanel, BoxLayout.X_AXIS));
        yetAnotherPanel.add(addUserLabelPanel);
        yetAnotherPanel.add(addUserToolPanel);
        
        addUserPanel.add(yetAnotherPanel);
        
        JPanel addUserButtonPanel = new JPanel(new FlowLayout());
        addUserButtonPanel.add(userAddButton);
        addUserPanel.add(addUserButtonPanel);
        
        //////// Delete Product Panel////////////////////
        JPanel delUserPanel = new JPanel();
        delUserPanel.setLayout(new BoxLayout(delUserPanel, BoxLayout.Y_AXIS));
        delUserPanel.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        JPanel delUserTitlePanel = new JPanel(new GridLayout(0,1));
        delUserTitlePanel.add(delUserTitleLabel);
        
        JPanel delUserLabelPanel = new JPanel();
        delUserLabelPanel.setLayout(new GridLayout(0, 1));
        delUserLabelPanel.add(delUserEmailLabel);
        
        JPanel delUserToolPanel = new JPanel();
        delUserToolPanel.setLayout(new GridLayout(0, 1));
        delUserToolPanel.add(delEmailComboBox);
        delEmailComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED){
                    selectedDeleteValue = delEmailComboBox.getSelectedIndex();
                    //JOptionPane.showMessageDialog(null, selectedValueChange);
                }
            }
        });
        
        delUserPanel.add(delUserTitlePanel);
        
        JPanel yetAnotherPanel2 = new JPanel();
        yetAnotherPanel2.setLayout(new BoxLayout(yetAnotherPanel2, BoxLayout.X_AXIS));
        yetAnotherPanel2.add(delUserLabelPanel);
        yetAnotherPanel2.add(delUserToolPanel);
        
        delUserPanel.add(yetAnotherPanel2);
        
        JPanel delProdButtonPanel = new JPanel(new FlowLayout());
        delProdButtonPanel.add(userDelButton);
        delUserPanel.add(delProdButtonPanel);
        /////////////////////////////////////////////////////
        
        
        ////////// Change Product Panel//////////////////////
        JPanel changeUserPanel = new JPanel();
        changeUserPanel.setLayout(new BoxLayout(changeUserPanel, BoxLayout.Y_AXIS));
        changeUserPanel.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        JPanel changeUserTitlePanel = new JPanel(new GridLayout(0,1));
        changeUserTitlePanel.add(changeUserTitleLabel);
        
        JLabel space = new JLabel();
        JPanel changeUserLabelPanel = new JPanel();
        changeUserLabelPanel.setLayout(new GridLayout(0, 1));
        changeUserLabelPanel.add(changeUserEmaliLabel);
        changeUserLabelPanel.add(space);
        changeUserLabelPanel.add(changeNewNameLabel);
        changeUserLabelPanel.add(changeNewEmailLabel);
        
        JPanel changeUserToolPanel = new JPanel();
        changeUserToolPanel.setLayout(new GridLayout(0, 1));
        changeUserToolPanel.add(changeEmailComboBox);
        changeEmailComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED){
                    selectedChangeValue = changeEmailComboBox.getSelectedIndex();
                    //JOptionPane.showMessageDialog(null, selectedValueChange);
                }
            }
        });
        changeUserToolPanel.add(changeUserSelectedButton);
        changeUserToolPanel.add(newNameField);
        changeUserToolPanel.add(newEmailField);
        
        changeUserPanel.add(changeUserTitlePanel);
        
        JPanel yetAnotherPanel3 = new JPanel();
        yetAnotherPanel3.setLayout(new BoxLayout(yetAnotherPanel3, BoxLayout.X_AXIS));
        yetAnotherPanel3.add(changeUserLabelPanel);
        yetAnotherPanel3.add(changeUserToolPanel);
        
        changeUserPanel.add(yetAnotherPanel3);
        
        JPanel changeUserButtonPanel = new JPanel(new FlowLayout());
        changeUserButtonPanel.add(changeUserButton);
        changeUserPanel.add(changeUserButtonPanel);
        //////////////////////////////////////////////////////////
        
        
        ////////////// New Category //////////////////////////////
        JPanel resetUserPanel = new JPanel();
        resetUserPanel.setLayout(new BoxLayout(resetUserPanel, BoxLayout.Y_AXIS));
        resetUserPanel.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        JPanel resetUserTitlePanel = new JPanel(new GridLayout(0,1));
        resetUserTitlePanel.add(resetUserTitleLabel);
        
        JPanel resetUserLabelPanel = new JPanel();
        resetUserLabelPanel.setLayout(new GridLayout(0, 1));
        resetUserLabelPanel.add(resetUserEmailLabel);
        
        JPanel resetUserToolPanel = new JPanel();
        resetUserToolPanel.setLayout(new GridLayout(0, 1));
        resetUserToolPanel.add(resetUserEmailComboBox);
        resetUserEmailComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED){
                    selectedResetValue = resetUserEmailComboBox.getSelectedIndex();
                    //JOptionPane.showMessageDialog(null, selectedValueChange);
                }
            }
        });
        
        resetUserPanel.add(resetUserTitlePanel);
        
        JPanel yetAnotherPanel4 = new JPanel();
        yetAnotherPanel4.setLayout(new BoxLayout(yetAnotherPanel4, BoxLayout.X_AXIS));
        yetAnotherPanel4.add(resetUserLabelPanel);
        yetAnotherPanel4.add(resetUserToolPanel);
        
        resetUserPanel.add(yetAnotherPanel4);
        
        JPanel resetUserButtonPanel = new JPanel(new FlowLayout());
        resetUserButtonPanel.add(resetUserButton);
        resetUserPanel.add(resetUserButtonPanel);
        ////////////////////////////////////////////////////////////
        
        
        
        variablePanel.add(addUserPanel);
        variablePanel.add(delUserPanel);
        variablePanel.add(changeUserPanel);
        variablePanel.add(resetUserPanel);
        
        return variablePanel;
    }
    public int getResetID(){
        return selectedResetValue;
    }
    
    public int getDeleteID(){
        return selectedDeleteValue;
    }

    public void addNewUserListener(ActionListener al){
        userAddButton.addActionListener(al);
    }
    
    public void addResetUserListener(ActionListener al){
        resetUserButton.addActionListener(al);
    }
    
    public void addDeleteUserListener(ActionListener al){
        userDelButton.addActionListener(al);
    }
    
    public void addChangeUserListener(ActionListener al){
        changeUserButton.addActionListener(al);
    }
    
    public void addSelectUserListener(ActionListener al){
        changeUserSelectedButton.addActionListener(al);
    }
    
    public void showUser(ArrayList<CustomerModel> list) throws SQLException
   {
       this.list = list;
       String email = "";
       for(int i = 0; i < list.size(); i++)
       {
           email = list.get(i).getUserEmail();
           //JOptionPane.showMessageDialog(null, email);
           resetUserEmailComboBox.addItem(email);
           changeEmailComboBox.addItem(email);
           delEmailComboBox.addItem(email);
       }
       
   }
    
    public void setResult(){
        newEmailField.setText(list.get(selectedChangeValue).getUserEmail());
        newNameField.setText(list.get(selectedChangeValue).getUserName());
    }
    
    public String getEmail(){
        return userNameField.getText();
    }
    public String getName(){
        return userEmailField.getText();
    }
    public String getPassword(){
        return userPasswordField.getText();
    }
}



