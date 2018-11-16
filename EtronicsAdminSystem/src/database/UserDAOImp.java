/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.ConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.customerModel;


/**
 *
 * @author XintingLi
 */
public class UserDAOImp implements UserDAO{
    
    ConnectionHandler connHan;
    Connection connect = null;
    Statement stmt;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public UserDAOImp() throws ClassNotFoundException, SQLException{
        
        connHan = ConnectionHandler.getInstance();
        connect = connHan.makeConnection();
        stmt = connect.createStatement();
    }
    public boolean insertUser(String uname, String uPw, String uemail, String role){
        String sql = "insert into etronics_users(username,email,password,role) values('" + uname + "','" + uemail + "','" + uPw + "','" + role +"')";
        int result = 0;
        try {
            result = result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result > 0){
             return true;
        }
        else
            return false;
    }
    
    public boolean checkIfExist(String email){
        String sql = "select id from etronics_users where email='" + email + "'";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs != null)
            return true;
        else
            return false;
    }
    
    public String checkUser(String email) throws SQLException{
        String sql = "select * from etronics_users where email='" + email + "'";
        ResultSet rs = null;
        String password = "";
        rs = stmt.executeQuery(sql);
        if(rs != null){
           while(rs.next()){
                password = rs.getString(4);
            }
        }
       //JOptionPane.showMessageDialog(null, "44763");
        return password;
        
        
        
    }
    
    public customerModel getUserDetail(String email) throws SQLException{
        customerModel user = new customerModel();
        String sql = "select * from etronics_users where email='" + email + "'";
        ResultSet rs = null;
        rs = stmt.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                user.setUserID(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserPW(rs.getString(4));
                user.setUserEmail(rs.getString(3));
                user.setUserRole();
            }
        }
        else
            user = null;
        return user;
    }
    
    public ArrayList<customerModel> queryUsers() throws SQLException{
        ArrayList<customerModel> list = new ArrayList<>();
        String sql = "select * from etronics_users";
        ResultSet rs = null;
        rs = stmt.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
            customerModel user = new customerModel();
            user.setUserID(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setUserPW(rs.getString(4));
            user.setUserEmail(rs.getString(3));
            user.setUserRole();
            list.add(user);
            }
        }
        else
            list = null;
        return list;
    }
    
    public boolean deleteUser(int uID){
        String sql = "delete from etronics_users where id=" + uID;
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result > 0){
             return true;
        }
        else
            return false;
    }
    
    public boolean resetUser(int uID){
        String sql = "update etronics_users set password='123456' where id=" + uID;
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result > 0){
             return true;
        }
        else
            return false;
    }
    
    public boolean updateUser(int id, String uname, String uemail){
        String sql = "update etronics_users set";
        if(uname != null && !("".equals(uname))){
            sql += "username = '" + uname + "'";
        }
        
        if(uemail != null && !("".equals(uemail))){
            sql += "email = '" + uemail + "'";
        }
       
        sql += "where id=" + id;
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result > 0){
             return true;
        }
        else
            return false;
    }
    
    public String getUserRole(String email) throws SQLException{
        String role = "";
        String sql = "select * from etronics_users where email='" + email + "'";
        ResultSet rs = null;
        
        rs = stmt.executeQuery(sql);
        if(rs != null){
           while(rs.next()){
                role = rs.getString(5);
            }
        }
        return role;
    }
}
