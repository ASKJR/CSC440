package util;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

	private final int EMAIL_NOT_SENT = -1; 
	
    private final static String SENDER_ADDRESS = "rsoliveira.computacao@gmail.com";
    private final static String PASSWORD = "fusrohdah";
    
    public int sendMessage
    	(
    		String subject, 
    		String content,
    		String destinationAddress
    	)
    {
    	// Creating the message, assigning sender and password, retrieving a session
        MimeMessage message = new MimeMessage
		(
			EmailFactory.openSession
			(
				SENDER_ADDRESS, 
				PASSWORD
			)
		);

        // Adding the destinationAddress as a recipient
        try {
			message.addRecipient
			(
				Message.RecipientType.BCC, new InternetAddress
				(
					destinationAddress
				)
			);
		} catch (MessagingException e1) {
			return EMAIL_NOT_SENT;
		}
        
        // sending the message
	    try
	    { 
	        message.setSubject(subject);
	        Multipart multipart = new MimeMultipart();
	        
	        MimeBodyPart attachment0 = new MimeBodyPart();
	        attachment0.setContent(content, "text/html; charset=UTF-8");
	        multipart.addBodyPart(attachment0);
	        
	        message.setContent(multipart);            
	        Transport.send(message);

	    }catch(MessagingException e){
	        return EMAIL_NOT_SENT;
	    }
	    return 0;
    }
}
