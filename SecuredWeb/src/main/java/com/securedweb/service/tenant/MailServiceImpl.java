package com.securedweb.service.tenant;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.securedweb.model.tenant.Mail;

@Service("mailService")
public class MailServiceImpl implements MailService {
 
	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
	
    @Autowired
    JavaMailSender mailSender;
 
    @Override
    @Async
    public void sendEmail(Object object) {
 
        Mail mail = (Mail) object;
 
        MimeMessagePreparator preparator = getMessagePreparator(mail);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private MimeMessagePreparator getMessagePreparator(final Mail mail) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	mimeMessage.setFrom( new InternetAddress(mail.getFrom()));  
            	mimeMessage.setRecipient(Message.RecipientType.TO,
                     new InternetAddress(mail.getTo()));
            	mimeMessage.setText("Please click on the link " + mail.getBody()
                     + ", to reset password.");
            	mimeMessage.setSubject("Password Reset Request");
            }
        };
        return preparator;
    }
}