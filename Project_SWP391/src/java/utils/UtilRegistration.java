/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class UtilRegistration {
    public static void sendRegistration(String email, String pass){
         // pass: zmyy mcjd fbjt suyx
        // email: huun6458@gmail.com
        
        final String from = "huun6458@gmail.com";
        final String password = "zmyy mcjd fbjt suyx";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587");
        
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password); 
            }
        };
        
        // Phien lam viec 
        Session session = Session.getInstance(props,auth);
        
        try{
            MimeMessage mes = new MimeMessage(session);
            mes.setFrom(from);
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            mes.setSubject("Account login to system");
            mes.setText("Dear user,\n\n"
                    + "To log in to the account containing the course, use the account with email:  \n\n"
                    + email + "  \n\n  and pass password: \n\n"
                    + pass);
            
            Transport.send(mes);
            System.out.println("Email send successfully");
        } catch(Exception e){
            
        }
    }
   
}
