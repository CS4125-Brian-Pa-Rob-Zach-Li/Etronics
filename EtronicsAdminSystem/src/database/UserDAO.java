/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;
import user.CustomerModel;

/**
 *
 * @author XintingLi
 */
public interface UserDAO {
    public boolean insertUser(String uname, String uPw, String uemail, String role);
    public boolean checkIfExist(String email);
    public String checkUser(String email) throws SQLException;
    public CustomerModel getUserDetail(String email) throws SQLException;
    public ArrayList<CustomerModel> queryUsers() throws SQLException;
    public boolean deleteUser(int uID);
    public boolean resetUser(int uID);
    public boolean updateUser(int id, String uname, String uemail);
    public int getUser();
}
