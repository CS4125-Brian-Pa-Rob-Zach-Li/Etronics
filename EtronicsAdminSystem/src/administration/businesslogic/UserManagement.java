/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration.businesslogic;

import database.UserDAO;
import database.UserDAOImp;
import java.sql.SQLException;
import java.util.ArrayList;
import user.customerModel;

/**
 *
 * @author XintingLi
 */
public class UserManagement {
    UserDAOImp userDao;
    customerModel user = new customerModel();
    public UserManagement() throws ClassNotFoundException, SQLException{
        userDao = new UserDAOImp();
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
    
    public ArrayList<customerModel> queryUser() throws SQLException{
        ArrayList<customerModel> list = new ArrayList<customerModel>();
        list = userDao.queryUsers();
        return list;
    }
}
