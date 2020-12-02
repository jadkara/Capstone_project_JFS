package com.wipro.service;

import java.io.File;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.model.Challenger;
import com.wipro.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendWelcomeEmail(String emailId, String fullname) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String welcomeMessage = "Hello "+ fullname +",\nWelcome to Diet plan.\nYour default password for login is :password@123";
		simpleMailMessage.setTo(emailId);
		simpleMailMessage.setSubject("Diet Program Membership Update");
		simpleMailMessage.setText(welcomeMessage);
		javaMailSender.send(simpleMailMessage);
	}

	public void sendRejectionEmail(String emailId, String fullname, String rejectionReason) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String rejectMessage = "Hello "+ fullname +",\nRegret to inform you, you're not eligible to Diet plan because "+rejectionReason +" .";
		simpleMailMessage.setTo(emailId);
		simpleMailMessage.setSubject("Diet Program Membership Update");
		simpleMailMessage.setText(rejectMessage);
		javaMailSender.send(simpleMailMessage);
	}
	
	public void sendMotivation(List<User> users, String fileToAttach) throws MessagingException 
	{
		for(User user : users) {
			MimeMessage mailMessage = javaMailSender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
	         helper.setTo(user.getEmail());
	         helper.setText("<html><body><h1>hello Welcome!</h1><body></html>", true);
	         helper.addAttachment("test.jpeg", new File(fileToAttach));
	         helper.setSubject("Hi");
	         javaMailSender.send(mailMessage);
		}
	}
	
	public void sendMotivationWithAttachment(MultipartFile file, String[] senders, String message, String fileName) throws MessagingException 
	{
		for(String email : senders) {
			MimeMessage mailMessage = javaMailSender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
	         helper.setTo(email);
			 String messageBody = "<html><body><h3>Dear User!</h3><br><br>"+ message +"<body></html>";
	         helper.setText(messageBody, true);
			 if(null != file) {
				helper.addAttachment(fileName, file);
			 }
			 helper.setSubject("Diet Program - Updates");
	         javaMailSender.send(mailMessage);
		}
	}

}
