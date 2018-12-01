/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import customer.businesslogic.register;
import java.sql.SQLException;
 
public class TestRegister {
    register testReg;
    String name, password, email;
    
    
    public TestRegister() {
    }
    
    
    @Before
    public void setUp() {
        try{
            testReg = new register();
        }catch(ClassNotFoundException | SQLException e){
            System.out.print(e);
        }   
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterLogic_Valid(){
        name = "Test";
        password = "Test1234";
        email = "Test@email.com";
        
        testReg.newRegister(name, password, email);
        testCheckOfInput(true);
        assertTrue(testReg.validateEmail(email));
        assertTrue(testReg.validatePW(password));
    }
    
    @Test
    public void  testRegisterLogic_InValid()
    {
        name = "";
        password = null;
        email = "";
        testReg.newRegister(name, password, email);
        testCheckOfInput(false);
        assertFalse(testReg.validateEmail(email));
        assertFalse(testReg.validatePW(password));
    }
    
    public void testCheckOfInput(boolean checkValue)
    {
        assertEquals(testReg.checkUserName(name), checkValue);
        assertEquals(testReg.checkUserPW(password), checkValue);
        assertEquals(testReg.checkEmail(email), checkValue);
    }
}
