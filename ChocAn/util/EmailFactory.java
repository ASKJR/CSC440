package util;

import java.util.Properties;

import javax.mail.*;

public class EmailFactory {
    private EmailFactory() {}

    public static Session openSession(String senderAddress, String password)
    {
        final String senderAddressTemp = senderAddress;
        final String passwordTemp =  password;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            { 
                return new PasswordAuthentication(senderAddressTemp, passwordTemp);
            }
        });
        
        return session;
    }
    
}
