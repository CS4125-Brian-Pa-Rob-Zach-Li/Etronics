package administration.gui;

import administration.UIAdminController;
import administration.UIAdminModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import administration.businesslogic.UserManagement; 
import customer.gui.loginGUI;
import javax.swing.JLabel;
 
public class mainFrame extends JFrame implements ActionListener{
  
    private JButton productManButton;
    private JButton userManButton;
    private JButton storeManButton;
    private JButton promoManButton;
    private JButton adManButton;
    private JButton logoutButton;
    private static UserManagementView userManagementPage;
    private static UserManagement userManagementBL;
    private JPanel cardpan; 
    private JPanel mainButtonPanel; 
    private JPanel pan1,pan2,pan3,pan4; //此处定义的面板中显示诗句
    private CardLayout card;      
    private static UIAdminController adminController;
    private static UIAdminModel adminModel;
    private static UIAdminView adminView;
    private static loginGUI loginPage;
 
 public mainFrame() throws SQLException, ClassNotFoundException{
    userManagementPage = new UserManagementView();
    loginPage = new loginGUI();
    userManagementBL = new UserManagement(userManagementPage); 
    adminModel = new UIAdminModel();
    adminView = new UIAdminView(adminModel);
    adminController = new UIAdminController(adminView, adminModel);
    
    cardpan=new JPanel();
    card=new CardLayout();
    cardpan.setLayout(card);
    JPanel mainButtonPanel = new JPanel();
    mainButtonPanel.setLayout(new GridLayout(0,1));
    mainButtonPanel.setBorder(new EmptyBorder(10, 0, 0, 10));
  
     productManButton = new JButton("Product Management");
    userManButton = new JButton("User Management");
    storeManButton = new JButton("Store Management");
    promoManButton = new JButton("Promotional Management");
    adManButton = new JButton("Advertising Management");
    logoutButton = new JButton("Logout");

  mainButtonPanel.add(logoutButton);
  mainButtonPanel.add(productManButton);
  mainButtonPanel.add(userManButton);
  mainButtonPanel.add(storeManButton);
  mainButtonPanel.add(promoManButton);
  mainButtonPanel.add(adManButton);
   
  pan1=new JPanel();
  logoutButton.addActionListener(this);
  productManButton.addActionListener(this);
  userManButton.addActionListener(this);
  storeManButton.addActionListener(this);
  promoManButton.addActionListener(this);
  adManButton.addActionListener(this);
   

  cardpan.add("userManagement",userManagementPage);
  cardpan.add("productManagement",adminView);

    this.add(mainButtonPanel,BorderLayout.WEST);
    this.add(cardpan,BorderLayout.CENTER);
    card.show(cardpan, "productManagement");
    this.setSize(900,600);
    this.setResizable(false);
    this.setTitle("Etronics Admin System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 }
    public void actionPerformed(ActionEvent e) {

        if("Product Management".equals(e.getActionCommand())){
         card.show(cardpan, "productManagement");
        }
        if("User Management".equals(e.getActionCommand())){
         card.show(cardpan, "userManagement");
        }
        if("Logout".equals(e.getActionCommand())){
         this.setVisible(false);
         loginPage.setVisible(true);
        }
    }
    
    public UIAdminController getController(){
        return adminController;
    }
}

