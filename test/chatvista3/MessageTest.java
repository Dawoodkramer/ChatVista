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
public class MessageTest {
    
    public MessageTest() {
    }
/**
     * Test message length validation - Success case
     */
    @Test
    public void testMessageLengthSuccess() {
        System.out.println("checkMessageLength - Success");
        String message = "Hi Mike, can you join us for dinner tonight";
        Message instance = new Message();
        String expResult = "Message ready to send.";
        String result = instance.checkMessageLength(message);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message length validation - Failure case (exceeds 250 characters)
     */
    @Test
    public void testMessageLengthFailure() {
        System.out.println("checkMessageLength - Failure");
        // Create a message longer than 250 characters
        String longMessage = "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. Extra text";
        Message instance = new Message();
        int excess = longMessage.length() - 250;
        String expResult = "Message exceeds 250 characters by " + excess + ", please reduce size.";
        String result = instance.checkMessageLength(longMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test recipient cell phone validation - Success case
     */
    @Test
    public void testRecipientCellSuccess() {
        System.out.println("checkRecipientCell - Success");
        String cellNumber = "+27718693002";
        Message instance = new Message();
        String expResult = "Cell phone number successfully captured.";
        String result = instance.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test recipient cell phone validation - Failure case
     */
    @Test
    public void testRecipientCellFailure() {
        System.out.println("checkRecipientCell - Failure");
        String cellNumber = "08575975889";
        Message instance = new Message();
        String expResult = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String result = instance.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test message hash creation with test data from requirements
     */
    @Test
    public void testCreateMessageHashTestCase1() {
        System.out.println("createMessageHash - Test Case 1");
        String messageID = "0012345678"; // Starting with 00
        int messageNumber = 1;
        String messageText = "Hi Mike, can you join us for dinner tonight";
        Message instance = new Message();
        String expResult = "00:1:HITONIGHT";
        String result = instance.createMessageHash(messageID, messageNumber, messageText);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message hash creation with test data case 2
     */
    @Test
    public void testCreateMessageHashTestCase2() {
        System.out.println("createMessageHash - Test Case 2");
        String messageID = "1234567890"; // Starting with 12
        int messageNumber = 2;
        String messageText = "Hi Keegan, did you receive the payment?";
        Message instance = new Message();
        String expResult = "12:2:HIPAYMENT";
        String result = instance.createMessageHash(messageID, messageNumber, messageText);
        assertEquals(expResult, result);
    }

    /**
     * Test message ID validation - Success case
     */
    @Test
    public void testCheckMessageIDSuccess() {
        System.out.println("checkMessageID - Success");
        String messageID = "1234567890"; // Exactly 10 characters
        Message instance = new Message();
        boolean expResult = true;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message ID validation - Failure case (too long)
     */
    @Test
    public void testCheckMessageIDFailure() {
        System.out.println("checkMessageID - Failure");
        String messageID = "12345678901"; // More than 10 characters
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);
    }

    /**
     * Test message ID generation
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        Message instance = new Message();
        String result = instance.generateMessageID();
        
        // Check that message ID is generated and is 10 characters long
        assertNotNull("Message ID should not be null", result);
        assertEquals("Message ID should be 10 characters long", 10, result.length());
        
        // Check that it contains only digits
        assertTrue("Message ID should contain only digits", result.matches("\\d{10}"));
    }

    /**
     * Test total messages counter
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Message instance = new Message();
        
        // Initially should be 0
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
        
        // Add a message and test again
        instance.addMessage("1234567890", "12:1:HITEST", "+27123456789", "Hi test", 1);
        expResult = 1;
        result = instance.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test print messages functionality
     */
    @Test
    public void testPrintMessagesEmpty() {
        System.out.println("printMessages - Empty");
        Message instance = new Message();
        String expResult = "No messages have been sent yet.";
        String result = instance.printMessages();
        assertEquals(expResult, result);
    }
    
    /**
     * Test print messages with actual messages
     */
    @Test
    public void testPrintMessagesWithContent() {
        System.out.println("printMessages - With Content");
        Message instance = new Message();
        
        // Add a test message
        instance.addMessage("1234567890", "12:1:HITEST", "+27123456789", "Hi test message", 1);
        
        String result = instance.printMessages();
        
        // Check that the message content is included
        assertTrue("Result should contain message ID", result.contains("1234567890"));
        assertTrue("Result should contain message hash", result.contains("12:1:HITEST"));
        assertTrue("Result should contain recipient", result.contains("+27123456789"));
        assertTrue("Result should contain message text", result.contains("Hi test message"));
    }

    /**
     * Test message hash with different message numbers in loop
     */
    @Test
    public void testCreateMessageHashLoop() {
        System.out.println("createMessageHash - Loop Test");
        Message instance = new Message();
        
        String[] testMessages = {
            "Hi Mike, can you join us for dinner tonight",
            "Hi Keegan, did you receive the payment?",
            "Hello world this is a test message"
        };
        
        String[] messageIDs = {"0012345678", "1234567890", "9876543210"};
        String[] expectedHashes = {"00:1:HITONIGHT", "12:2:HIPAYMENT", "98:3:HELLOMESSAGE"};
        
        for (int i = 0; i < testMessages.length; i++) {
            String result = instance.createMessageHash(messageIDs[i], i + 1, testMessages[i]);
            assertEquals("Hash for message " + (i + 1) + " should be correct", expectedHashes[i], result);
        }
    }

    /**
     * Test recipient cell phone validation with various formats
     */
    @Test
    public void testRecipientCellVariousFormats() {
        System.out.println("checkRecipientCell - Various Formats");
        Message instance = new Message();
        
        // Valid formats
        String[] validNumbers = {"+27718693002", "+27823456789", "+27987654321"};
        
        for (String number : validNumbers) {
            String result = instance.checkRecipientCell(number);
            assertEquals("Valid number " + number + " should be accepted", 
                        "Cell phone number successfully captured.", result);
        }
        
        // Invalid formats
        String[] invalidNumbers = {"08575975889", "27718693002", "+271234", "123456789"};
        
        for (String number : invalidNumbers) {
            String result = instance.checkRecipientCell(number);
            assertEquals("Invalid number " + number + " should be rejected", 
                        "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", 
                        result);
        }
    }
}