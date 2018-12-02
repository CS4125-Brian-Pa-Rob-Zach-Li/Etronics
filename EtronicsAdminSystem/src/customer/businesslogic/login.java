/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.businesslogic;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import user.User;
import user.CustomerModel;
import database.UserDAO;
import database.UserDAOImp;
import user.CustomerFactory;
import user.StuffFactory;
import user.StuffModel;
import user.UserDecorator;
import user.UserFactory;

/**
 *
 * @author XintingLi
 */
public class login {
    private UserDAOImp userDao;
    private User user;
    private String role;
    
    public login() throws ClassNotFoundException, SQLException{
       userDao = new UserDAOImp();
    }
    
    public String getDetails(String uemail, String uPw) throws SQLException{
        if(checkEmail(uemail) && checkUserPW(uPw)){
            if(userDao.checkIfExist(uemail) && checkUser(uemail, uPw)){
                    role = getRole(uemail);
                    createUser(role);
                    loginUser(uemail);
                    return "Successfully";
                }
            else
                return "Username/password not found."; 
        }
        else
            return "Please enter username/password";
    }
    
    public boolean checkUser(String uemail, String uPw) throws SQLException{
        String password = userDao.checkUser(uemail);
        if(password.equals(uPw))
            return true;
        else
            return false;
    }
    
    
    public void loginUser(String uemail) throws SQLException{
        user =  userDao.getUserDetail(uemail);
    }
    
    public boolean checkUserPW(String uPw){
        if(uPw == null || "".equals(uPw))
        {
            return false;
        }
        else
             return true;
    }
    
    public boolean checkEmail(String email){
        if(email == null || "".equals(email))
        {
            return false;
        }
        else
            return true;
    }
    
    public boolean validatePW(String text){
        String pattern = "^[0-9a-zA-Z]{6,16}";
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
    
    public String setCustomerDetails(String uemail) throws SQLException
    {
        user = new UserDecorator(user);
        user = userDao.getUserDetail(uemail);
        return user.getUserName();
    }
    
    public String getRole(String email) throws SQLException
    {
        String role = userDao.getUserRole(email);
        return role;
    }
    
    public void createUser(String role)
    {
        if(role.equals("stuff"))
        {
            UserFactory ufactory = new StuffFactory();
            user = ufactory.getuser();
            //user = new stuffModel();
        }
        else
        {
             UserFactory ufactory = new CustomerFactory();
           user = ufactory.getuser();
            //user = new customerModel();
        }
    }
}
