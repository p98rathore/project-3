
package in.co.rays.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class Sendmail.
 */
public class Sendmail {

/** The Constant SMTP_SERVER. */
private static final String SMTP_SERVER="smtp.gmail.com";

/** The Constant SMTP_PORT. */
private static final int SMTP_PORT=465;

/** The Constant LOGIN. */
private static final String LOGIN="p98rathore@gmail.com";

/** The Constant PASS. */
private static final String PASS="8109888971";

/** The pro. */
private static Properties pro= new Properties();


static{
	pro.put("mail.transport.protocol", "smtps");
	pro.put("mail.smtps.host", SMTP_SERVER);
	pro.put("mail.smtps.port", SMTP_PORT);
	pro.put("mail.smtp.auth", "true");
}

/** The from. */
private static String from="p98rathore@gmail.com";

/** The to. */
private static String to="rahulsirwani181989@gmail.com";

/**
 * The main method.
 *
 * @param args the arguments
 * @throws AddressException the address exception
 * @throws MessagingException the messaging exception
 */
public static void main(String[] args) throws AddressException, MessagingException {
	sendTextMail();
	
}

/**
 * Send text mail.
 *
 * @throws AddressException the address exception
 * @throws MessagingException the messaging exception
 */
private static void sendTextMail() throws AddressException, MessagingException {
	String subject="My first email from javaMail API";
	String msg= "hi  this is message from pradeep rathore";
	
	
	Session session = Session.getDefaultInstance(pro);
	
	MimeMessage message=new MimeMessage(session);
	
	
	
	
	message.setFrom(new InternetAddress(from));
	
	
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
	 message.setText(msg);
	 message.setSubject(subject);
	 
	 
	 Transport tx= session.getTransport();
	 tx.connect(SMTP_SERVER, SMTP_PORT, LOGIN,PASS);
	 
	 tx.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	 
	 
	 tx.close();
}




}
