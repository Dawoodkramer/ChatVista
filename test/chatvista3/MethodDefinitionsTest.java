/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package chatvista3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class MethodDefinitionsTest {
    
    public MethodDefinitionsTest() {
    }

    /**
     * Test of checkUserName method, of class MethodDefinitions.
     */
    @Test
    public void testCorrectCheckUserName() {
        System.out.println("checkUserName");
        String username = "ky_1";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testIncorrectCheckUserName() {
        System.out.println("checkUserName");
        String username = "kyle!!!!!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkPassword method, of class MethodDefinitions.
     */
    @Test
    public void testCheckPasswordMeetsRequirements() {
        System.out.println("checkPassword");
        String password = "Ch&u&sec@ke99!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = true;
        boolean result = instance.checkPassword(password);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testCheckPasswordDoesNotMeetsRequirements() {
        System.out.println("checkPassword");
        String password = "password!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = false;
        boolean result = instance.checkPassword(password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkCellPhoneNumber method, of class MethodDefinitions.
     */
    @Test
    public void testCheckCorrectCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellnumber = "+27838968976";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellnumber);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testCheckIncorrectCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellnumber = "08966553";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellnumber);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of loginUser method, of class MethodDefinitions.
     */
    @Test
    public void testLoginUserSuccessful() {
        System.out.println("loginUser");
        String inputUsername = "ky_1";
        String inputPassword = "Ch&u&sec@ke99!";
        String originalUsername = "ky_1";
        String originalPassword = "Ch&u&sec@ke99!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = true;
        boolean result = instance.loginUser(inputUsername, inputPassword, originalUsername, originalPassword);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void testLoginUserFailWrongPassword() {
        System.out.println("loginUser");
        String inputUsername = "ky_1";
        String inputPassword = "password";
        String originalUsername = "ky_1";
        String originalPassword = "Ch&u&sec@ke99!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = false;
        boolean result = instance.loginUser(inputUsername, inputPassword, originalUsername, originalPassword);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testLoginUserFailWrongUsername() {
        System.out.println("loginUser");
        String inputUsername = "ky1!!!!!!!";
        String inputPassword = "password";
        String originalUsername = "ky_1";
        String originalPassword = "Ch&u&sec@ke99!";
        MethodDefinitions instance = new MethodDefinitions();
        boolean expResult = false;
        boolean result = instance.loginUser(inputUsername, inputPassword, originalUsername, originalPassword);
        assertEquals(expResult, result);
        
    }

   
    
}
