/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatvista3;
import javax.swing.JOptionPane;
/**
 *
 * @author lab_services_student
 */
public class ChatVista3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // Welcome message for QuickChat - First thing user sees
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        
        //Declaration of the variables that will be used
        String username = "";
        String password = "";
        String cellnumber = "";
        String firstName = "";
        String lastName = "";
        boolean isRegistered = false;
        boolean isLoggedIn = false;
        
        //Creating an object to instantiate the methods from the MethodDefinitions class
        MethodDefinitions li = new MethodDefinitions();
        
        // Main application loop - Login/Register/Exit menu
        while (true) {
            // Display initial menu options
            String initialChoice = JOptionPane.showInputDialog(
                "Please select an option:\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit"
            );
            
            if (initialChoice == null || initialChoice.equals("3")) {
                // User chose to exit or cancelled
                JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                break;
            } else if (initialChoice.equals("1")) {
                // Login option - redirect to register
                if (!isRegistered) {
                    JOptionPane.showMessageDialog(null, "Please create an account to register.");
                    continue; // Take them back to the menu
                } else {
                    // If already registered, allow login
                    loginUser(li, username, password, firstName, lastName);
                    isLoggedIn = true;
                    break; // Exit to messaging menu
                }
            } else if (initialChoice.equals("2")) {
                // Register option - use existing registration code
                Object[] registrationResult = registerUser(li);
                if (registrationResult != null) {
                    username = (String) registrationResult[0];
                    password = (String) registrationResult[1];
                    cellnumber = (String) registrationResult[2];
                    firstName = (String) registrationResult[3];
                    lastName = (String) registrationResult[4];
                    isRegistered = true;
                    
                    JOptionPane.showMessageDialog(null, "Registration completed successfully! You can now login.");
                }
            } else {
                // Invalid option
                JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, or 3.");
            }
        }
        
        // Part 2: QuickChat messaging functionality - only if registered and logged in
        if (isRegistered && isLoggedIn) {
            // Creating Message object for messaging functionality
            Message messageHandler = new Message();
            
            // Messaging menu loop
            while (true) {
                // Display messaging menu options
                String menuChoice = JOptionPane.showInputDialog(
                    "Please select an option:\n" +
                    "1) Send Messages\n" +
                    "2) Show recently sent messages\n" +
                    "3) Quit"
                );
                
                if (menuChoice == null || menuChoice.equals("3")) {
                    // User chose to quit or cancelled
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                    break;
                } else if (menuChoice.equals("1")) {
                    // Send Messages functionality
                    sendMessages(messageHandler);
                } else if (menuChoice.equals("2")) {
                    // Show recently sent messages - still in development
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                } else {
                    // Invalid option
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, or 3.");
                }
            }
        }
    }
    
    /**
     * Method to handle user registration using existing validation methods
     * @param li The MethodDefinitions object containing validation methods
     * @return Object array containing registration data [username, password, cellnumber, firstName, lastName]
     */
    private static Object[] registerUser(MethodDefinitions li) {
        String username;
        String password;
        String cellnumber;
        String firstName;
        String lastName;
        
        // Get user's first and last name
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        if (firstName == null) return null; // User cancelled
        
        lastName = JOptionPane.showInputDialog("Enter your last name:");
        if (lastName == null) return null; // User cancelled
        
        //Using a while loop so that when the user enters incorrect data
        //they will be prompted to enter again.
        while (true) {
            username = JOptionPane.showInputDialog("Enter a username 5 characters long \n It must contain an underscore:");
            
            if (username == null) return null; // User cancelled
            
        //Instantiating the method check user name that has the parameters for the username .   
            if (li.checkUserName(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore \n and is no more than five characters in length.");
            }
        }
        
        while (true) {
            password = JOptionPane.showInputDialog("Enter a password of 8 characters or more.\n Include a capital letter, number, and special character.");
            
            if (password == null) return null; // User cancelled
            
            //Instantiating the method check password that has the parameters for the password.
            if (li.checkPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, \n a capital letter, a number, and a special character.");
            }
        }
        
        while (true) {
            cellnumber =  JOptionPane.showInputDialog("Enter a cell phone number \n include +27 and 9 digits after it):");
            
            if (cellnumber == null) return null; // User cancelled
            
            //Instantiating the method check cell phone number that has the parameters for the cell phone number.
            if (li.checkCellPhoneNumber(cellnumber)) {
                JOptionPane.showMessageDialog(null, "Cell number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted \n or does not contain international code");
            }
        }
        
        // Final registration confirmation using the methods check username and check password to match what the user has entered.
        // Instead of typing out the display message again I have it typed in the other class already 
        // All I did was create a var to store the message that I'm calling from the other class.
        String regMessage = li.registerUser(username, password);
        JOptionPane.showMessageDialog(null, regMessage);
        
        return new Object[]{username, password, cellnumber, firstName, lastName};
    }
    
    /**
     * Method to handle user login
     * @param li The MethodDefinitions object containing login methods
     * @param registeredUsername The registered username
     * @param registeredPassword The registered password  
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    private static void loginUser(MethodDefinitions li, String registeredUsername, String registeredPassword, String firstName, String lastName) {
       // Prompting the user for login credentials 
       // The username and password they originally chose
       String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
       String loginPassword = JOptionPane.showInputDialog("Enter your password to login:");

       // Calling loginUser method
       boolean isLoginSuccessful = li.loginUser(loginUsername, loginPassword, registeredUsername, registeredPassword);

       // Displaying login status
       // Calling the first and second name
       String loginMessage = li.returnLoginStatus(isLoginSuccessful, firstName, lastName);
       JOptionPane.showMessageDialog(null, loginMessage);
    }

    /**
     * Method to handle sending multiple messages
     * @param messageHandler The Message object to handle message operations
     */
    private static void sendMessages(Message messageHandler) {
        // Ask user how many messages they want to send
        String numMessagesStr = JOptionPane.showInputDialog("How many messages would you like to send?");
        
        if (numMessagesStr == null) {
            return; // User cancelled
        }
        
        try {
            int numMessages = Integer.parseInt(numMessagesStr);
            
            if (numMessages <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of messages.");
                return;
            }
            
            // Loop to send the specified number of messages
            for (int i = 1; i <= numMessages; i++) {
                JOptionPane.showMessageDialog(null, "Sending message " + i + " of " + numMessages);
                
                // Get recipient number
                String recipient = "";
                while (true) {
                    recipient = JOptionPane.showInputDialog("Enter recipient's cell phone number:");
                    
                    if (recipient == null) {
                        return; // User cancelled
                    }
                    
                    String recipientValidation = messageHandler.checkRecipientCell(recipient);
                    if (recipientValidation.equals("Cell phone number successfully captured.")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, recipientValidation);
                    }
                }
                
                // Get message text
                String messageText = "";
                while (true) {
                    messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
                    
                    if (messageText == null) {
                        return; // User cancelled
                    }
                    
                    String messageValidation = messageHandler.checkMessageLength(messageText);
                    if (messageValidation.equals("Message ready to send.")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, messageValidation);
                    }
                }
                
                // Generate message ID and hash
                String messageID = messageHandler.generateMessageID();
                String messageHash = messageHandler.createMessageHash(messageID, i, messageText);
                
                // Ask user what to do with the message
                String action = messageHandler.sentMessage();
                
                if (action.equals("Send")) {
                    // Add message to sent messages
                    messageHandler.addMessage(messageID, messageHash, recipient, messageText, i);
                    
                    // Display message details
                    String messageDetails = "Message Details:\n" +
                                          "Message ID: " + messageID + "\n" +
                                          "Message Hash: " + messageHash + "\n" +
                                          "Recipient: " + recipient + "\n" +
                                          "Message: " + messageText;
                    
                    JOptionPane.showMessageDialog(null, messageDetails);
                    
                    // Store message in JSON file
                    messageHandler.storeMessage(messageID, messageHash, recipient, messageText, i);
                    
                    JOptionPane.showMessageDialog(null, "Message successfully sent.");
                } else if (action.equals("Disregard")) {
                    JOptionPane.showMessageDialog(null, "Press O to delete message.");
                    i--; // Don't count this message
                } else if (action.equals("Store")) {
                    // Store message without sending
                    messageHandler.storeMessage(messageID, messageHash, recipient, messageText, i);
                    JOptionPane.showMessageDialog(null, "Message successfully stored.");
                    i--; // Don't count this message as sent
                }
            }
            
            // Display total messages sent
            int totalSent = messageHandler.returnTotalMessages();
            JOptionPane.showMessageDialog(null, "Total messages sent: " + totalSent);
            
            // Display all sent messages
            String allMessages = messageHandler.printMessages();
            if (!allMessages.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All sent messages:\n" + allMessages);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
        }
    }
}
