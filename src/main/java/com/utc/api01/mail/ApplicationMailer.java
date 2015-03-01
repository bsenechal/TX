package com.utc.api01.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service("mailService")
public class ApplicationMailer
{
    @Autowired
    private MailSender mailSender;
     
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    /**
     * This method will send compose and send the message
     * */
    public void sendMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
 
    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message, String to)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setTo(to);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
