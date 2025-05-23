/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatvista3;

/**
 *
 * @author lab_services_student
 */
public class MethodDefinitions {
    
    //Method to check if the username meets the conditions
    
    public boolean checkUserName(String username) {   
    return username.length() <= 5 && username.contains("_"); 
    }
    
    //Method to check if the password meets the conditions
    
    public boolean checkPassword(String password) {   
    return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[^a-zA-Z0-9].*");
    }
    
    //Method to check if the cell number meets the conditions
    
    public boolean checkCellPhoneNumber(String cellnumber){
    // ChatGPT regex cell phone checker
    
    return cellnumber.matches("^\\+27\\d{9}$");
    
    }
    
    //Method to check if the username and password are correct inorder to register the user
    
    public String registerUser(String username, String password) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted.";
        }
        if (!checkPassword(password)) {
            return "Password does not meet complexity requirements.";
        }
        return "User registered successfully.";
    }
    
    // Method to verify login credentials the user must re-enter the user name and password
    // now that is has been entered twice and and they are stored in different variables
    // they are being compared and checked
    public boolean loginUser(String inputUsername, String inputPassword, String originalUsername, String originalPassword) {
    return inputUsername.equals(originalUsername) && inputPassword.equals(originalPassword);
}
    
    // Method to return login status
    // Variables that will be used are the first name and last name upon success
    
    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
    if (loginSuccess) {
        return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
    }
    
  }
    

