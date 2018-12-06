/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration.businesslogic;

import administration.gui.UserManagementView;
import customer.businesslogic.register;
import database.UserDAO;
import database.UserDAOImp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.CustomerModel;

/**
 *
 * @author XintingLi
 */
public class UserManagement {
    UserDAOImp userDao;
    CustomerModel user = new CustomerModel();
    private static UserManagementView view;
    ArrayList<CustomerModel> list;
    public UserManagement(UserManagementView view) throws ClassNotFoundException, SQLException{
        userDao = new UserDAOImp();
        list = new ArrayList<CustomerModel>();
        list = queryUser();
        this.view = view;
        view.showUser(list);
        addSelectUserListeners();
        addResetUserListeners();
        addDeleteUserListeners();
        addAddUserListeners();
        addChangeUserListeners();
    }
    
    public String deleteUser(int uID){
        
        boolean flag = userDao.deleteUser(uID);
        if(flag){
            return "Successfully";
        }
        else
            return "Delete failed";
    }
    
    public String resetUser(int uID){
        boolean flag = userDao.resetUser(uID);
        if(flag){
            return "Successfully";
        }
        else
            return "Delete failed";
    }
    
    public String updateUser(int id, String uname,  String uemail){
        boolean flag = userDao.updateUser(id, uname, uemail);
        if(flag){
            return "Successfully";
        }
        else
            return "Delete failed";
    }
    
    public String addUser(String uname, String uPw, String uemail){
        boolean flag = userDao.insertUser(uname, uPw, uemail,"custormer");
        if(flag){
            return "Successfully";
        }
        else
            return "Delete failed";
    }
    
    public ArrayList<CustomerModel> queryUser() throws SQLException{
        ArrayList<CustomerModel> list = new ArrayList<CustomerModel>();
        list = userDao.queryUsers();
        return list;
    }
    
    public static void addSelectUserListeners(){
        view.addSelectUserListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                view.setResult();
            }});
    }
     public void addResetUserListeners(){
        view.addResetUserListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selectID = view.getResetID();
                String result = resetUser(list.get(selectID).getUserID());
                JOptionPane.showMessageDialog(null, result);
            }});
    }
      public void addDeleteUserListeners(){
        view.addDeleteUserListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selectID = view.getDeleteID();
                String result = deleteUser(list.get(selectID).getUserID());
                JOptionPane.showMessageDialog(null, result);
                try {
                    list = queryUser();
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    view.showUser(list);
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
    }
       public void addAddUserListeners(){
        view.addNewUserListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               
            register newRegister = null;
                try {
                    newRegister = new register();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
    
            String uname = view.getEmail();
            String password = view.getName();
            String email = view.getPassword();
            if(!validateEmail(email))
                JOptionPane.showMessageDialog(null, "Email invalud"); 
            else if(!validatePW(password))
                JOptionPane.showMessageDialog(null, "Password invalud"); 
            else{
                String result = newRegister.getDetails(uname, password, email);
                JOptionPane.showMessageDialog(null, result); 
            }
            try {
                    list = queryUser();
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    view.showUser(list);
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }});
    }
       public void addChangeUserListeners(){
        view.addChangeUserListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selectID = view.getResetID();
                String result = updateUser(list.get(selectID).getUserID(), list.get(selectID).getUserName(), list.get(selectID).getUserEmail());
                JOptionPane.showMessageDialog(null, result);
            }});
    }
       public boolean validatePW(String text){
        String pattern = "^[0-9a-zA-Z]{8,16}";
        if(!(text.matches(pattern))){
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean validateEmail(String text){
        String pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(!(text.matches(pattern))){
            return false;
        }
        else{
            return true;
        }
    }
}
