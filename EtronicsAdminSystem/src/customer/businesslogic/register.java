/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.businesslogic;

import database.UserDAO;
import database.UserDAOImp;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import user.User;
import user.customerModel;

/**
 *
 * @author XintingLi
 */
public class register {
    UserDAOImp userDao;
    customerModel user = new customerModel();
    public register() throws ClassNotFoundException, SQLException{
        userDao = new UserDAOImp();
	}
            
    public String getDetails(String uname, String uPw, String uemail){
        if( checkEmail(uemail) && checkUserPW(uPw) && checkUserName(uname))
        {
            if(!userDao.checkIfExist(uemail))
            {
                newRegister(uname, uPw, uemail);
                userDao.insertUser(uname, uPw, uemail,"custormer");
                return "Successfully";
            }
            else
                return "User has existed"; 
        }
        else
            return "Please enter username/password";
    }
    
    public void newRegister(String uname, String uPw, String uemail)
    {
        user.setUserName(uname);
        user.setUserPW(uPw);
        user.setUserEmail(uemail);
    }
    
    public boolean checkUserName(String uname)
    {
        if(uname == null || "".equals(uname))
        {
            return false;
        }
        else
        {
            
             return true;
        }
    }
    
    public boolean checkUserPW(String uPw)
    {
        if(uPw == null || "".equals(uPw))
        {
            return false;
        }
        else
        {
            
        }
            return true;
    }
    
    public boolean checkEmail(String email)
    {
        if(email == null || "".equals(email))
        {
            return false;
        }
        else
            return true;
    }
    
    
}
