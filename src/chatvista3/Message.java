/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatvista3;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author lab_services_student
 */
/**
 * Message class to handle messaging functionality for QuickChat
 * Contains methods for message validation, creation, and storage
 * 
 * AI Attribution: JSON storage functionality was generated using ChatGPT (OpenAI, 2024)
 * to create methods for storing messages in JSON format.
 * 
 * @author lab_services_student
 */
public class Message {
    
    private ArrayList<String> sentMessages;
    private int totalMessagesSent;
    
    // Constructor to initialize the message handler
    public Message() {
        sentMessages = new ArrayList<>();
        totalMessagesSent = 0;
    }
    
    /**
     * Method to check if the message ID is not more than ten characters
     * @param messageID The message ID to validate
     * @return true if message ID is valid, false otherwise
     */
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }
    
    /**
     * Method to check if the recipient cell number is no more than ten characters long and starts with +27
     * @param cellNumber The cell phone number to validate
     * @return String message indicating validation result
     */
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        
        // Check if it starts with +27 and has exactly 12 characters total (+27 + 9 digits)
        if (cellNumber.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    /**
     * Method to check message length
     * @param message The message text to validate
     * @return String message indicating validation result
     */
    public String checkMessageLength(String message) {
        if (message == null) {
            return "Please enter a message of less than 250 characters.";
        }
        
        if (message.length() > 250) {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        } else {
            return "Message ready to send.";
        }
    }
    
    /**
     * Method to generate a random 10-digit message ID
     * @return String containing the generated message ID
     */
    public String generateMessageID() {
        Random random = new Random();
        StringBuilder messageID = new StringBuilder();
        
        for (int i = 0; i < 10; i++) {
            messageID.append(random.nextInt(10));
        }
        
        return messageID.toString();
    }
    
    /**
     * Method to create and return the Message Hash
     * Format: First two digits of message ID : message number : first word + last word (all caps)
     * @param messageID The message ID
     * @param messageNumber The message number
     * @param messageText The message text
     * @return String containing the message hash
     */
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        // Get first two characters of message ID
        String firstTwoDigits = messageID.substring(0, 2);
        
        // Split message into words
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Create hash in format: XX:Y:FIRSTLAST (all caps)
        String hash = firstTwoDigits + ":" + messageNumber + ":" + 
                     (firstWord + lastWord).toUpperCase().replaceAll("[^A-Z]", "");
        
        return hash;
    }
    
    /**
     * Method to allow user to choose if they want to send, store, or disregard the message
     * @return String indicating the user's choice
     */
    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        
        int choice = JOptionPane.showOptionDialog(
            null,
            "What would you like to do with this message?",
            "Message Action",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        switch (choice) {
            case 0:
                return "Send";
            case 1:
                return "Disregard";
            case 2:
                return "Store";
            default:
                return "Disregard"; // If user closes dialog
        }
    }
    
    /**
     * Method to add a message to the sent messages list
     * @param messageID The message ID
     * @param messageHash The message hash
     * @param recipient The recipient's cell number
     * @param messageText The message text
     * @param messageNumber The message number
     */
    public void addMessage(String messageID, String messageHash, String recipient, String messageText, int messageNumber) {
        String messageDetails = "Message " + messageNumber + " - ID: " + messageID + 
                               ", Hash: " + messageHash + ", To: " + recipient + 
                               ", Text: " + messageText;
        sentMessages.add(messageDetails);
        totalMessagesSent++;
    }
    
    /**
     * Method to return a list of all the messages sent while the program is running
     * @return String containing all sent messages
     */
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        
        StringBuilder allMessages = new StringBuilder();
        for (int i = 0; i < sentMessages.size(); i++) {
            allMessages.append(sentMessages.get(i));
            if (i < sentMessages.size() - 1) {
                allMessages.append("\n\n");
            }
        }
        
        return allMessages.toString();
    }
    
    /**
     * Method to return the total number of messages sent
     * @return int total number of messages sent
     */
    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    /**
     * Method to store messages in JSON format
     * AI Attribution: This method was generated using ChatGPT (OpenAI, 2024)
     * to create functionality for storing messages in JSON format.
     * 
     * @param messageID The message ID
     * @param messageHash The message hash
     * @param recipient The recipient's cell number
     * @param messageText The message text
     * @param messageNumber The message number
     */
    public void storeMessage(String messageID, String messageHash, String recipient, String messageText, int messageNumber) {
        try {
            FileWriter fileWriter = new FileWriter("messages.json", true);
            
            // Create JSON object for the message
            String jsonMessage = "{\n" +
                               "  \"messageID\": \"" + messageID + "\",\n" +
                               "  \"messageNumber\": " + messageNumber + ",\n" +
                               "  \"messageHash\": \"" + messageHash + "\",\n" +
                               "  \"recipient\": \"" + recipient + "\",\n" +
                               "  \"messageText\": \"" + messageText + "\",\n" +
                               "  \"timestamp\": \"" + java.time.LocalDateTime.now() + "\"\n" +
                               "},\n";
            
            fileWriter.write(jsonMessage);
            fileWriter.close();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }
}


