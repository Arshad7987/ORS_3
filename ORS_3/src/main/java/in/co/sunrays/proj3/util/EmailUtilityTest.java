package in.co.sunrays.proj3.util;
 
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.util.EmailMessage;
import in.co.sunrays.proj3.util.EmailUtility;
 
import javax.mail.MessagingException;
 
/**
 * Testcase to test EmailUtility class
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
 
public class EmailUtilityTest {
 
    /**
     * Main method to call test methods.
     * 
     * @param args
     * @throws Exception 
     */
 
    public static void main(String[] args) throws Exception {
        testHTMLEmail();
    //   testTextEmail();
        //testEmailTORecipient();
        //testEmailCCRecipient();
        //testEmailBCCRecipient();
        //testEmailMultipleTORecipient();
        //testEmailMultipleCCRecipient();
        //testEmailMultipleBCCRecipient();
 
    }
 
    /**
     * Send HTML Email
     * @throws Exception 
     */
 
    public static void testHTMLEmail() throws Exception, ApplicationException {
        EmailMessage msg = new EmailMessage();
		msg.setTo("sahilkhan868.sk@gmail.com");
		msg.setSubject("Test : testHTMLEmail");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send TEXT Email
     * @throws Exception 
     */
 
    public static void testTextEmail() throws ApplicationException, Exception {
        EmailMessage msg = new EmailMessage();
		msg.setTo("sahilkhan868.sk@gmail.com");
		msg.setSubject("Test : testTextEmail");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.TEXT_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Single TO Recipient
     * @throws Exception 
     */
    public static void testEmailTORecipient() throws Exception, ApplicationException {
        EmailMessage msg = new EmailMessage();
		msg.setTo("sunrayssunilbook@gmail.com");
		msg.setSubject("Test : testEmailTORecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Single CC Recipient
     * @throws Exception 
     */
    public static void testEmailCCRecipient() throws Exception {
        EmailMessage msg = new EmailMessage();
		msg.setCc("sunrayssunilbook@gmail.com");
		msg.setSubject("Test : testEmailCCRecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.TEXT_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Single BCC Recipient
     * @throws Exception 
     */
    public static void testEmailBCCRecipient() throws Exception {
        EmailMessage msg = new EmailMessage();
		msg.setBcc("sunrayssunilbook@gmail.com");
		msg.setSubject("Test : testEmailBCCRecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Multiple To Recipient
     * @throws MessagingException 
     */
 
    public static void testEmailMultipleTORecipient() throws MessagingException, ApplicationException {
        EmailMessage msg = new EmailMessage();
		msg.setTo("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
		msg.setSubject("Test : testEmailMultipleTORecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Multiple CC Recipient
     * @throws Exception 
     */
    public static void testEmailMultipleCCRecipient() throws Exception {
        EmailMessage msg = new EmailMessage();
		msg.setCc("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
		msg.setSubject("Test : testEmailMultipleCCRecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
    /**
     * Send Email to Multiple BCC Recipient
     * @throws MessagingException 
     */
    public static void testEmailMultipleBCCRecipient() throws MessagingException, ApplicationException {
        EmailMessage msg = new EmailMessage();
		msg.setBcc("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
		msg.setSubject("Test : testEmailMultipleBCCRecipient");
		msg.setMessage("<h1>Hello world</h1>");
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
    }
 
}