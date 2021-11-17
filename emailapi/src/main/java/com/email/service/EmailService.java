package com.email.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.dao.EmailDao;
import com.email.model.Email;




@Service
public class EmailService implements Services {

	Logger logger=LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Autowired
	private EmailDao emaildao;

	
	 
	@Override
	public List<Email> getAllEmail() {
           
		logger.info("Success");
		return emaildao.findAll();
	}

	@Override
	public Email getByEmail(String email) {
         
		logger.info("Success");
		return emaildao.findById(email).orElse(null);
	}

	@Override
	public ResponseEntity<HttpStatus> addEmail(Email email) {
		Email x =  emaildao.save(email);
		sendEmail(email.getEmail() , email.getOtp());
		if(x!=null)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	private void sendEmail(String email, int otp) {
		 SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);
	        msg.setSubject("Generated Otp");
	        msg.setText(" Your OTP is  " + otp + " This OTP will expire in 10 minutes");
            javaMailSender.send(msg);
		
	}

	@Override
	public ResponseEntity<HttpStatus> OtpValidate(Email eo) {
		Email emailData = getByEmail(eo.getEmail());
			if(emailData == null) 
			{
				      logger.info("Matching issue");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		else 
		{
			if(emailData.getOtp()==(eo.getOtp()))
			{
				LocalDateTime start = emailData.getGeneratedTime();
				LocalDateTime end = emailData.getExpiryTime();
				LocalDateTime curr = LocalDateTime.now();
				System.out.println(ChronoUnit.MINUTES.between(start , curr));
				System.out.println(ChronoUnit.MINUTES.between(curr , end));
				if(ChronoUnit.MINUTES.between(start , curr) <= 10 && ChronoUnit.MINUTES.between(curr , end) >= 0)
					{
					           logger.info("Success");
							return new ResponseEntity<>(HttpStatus.OK);
					}
				else
				{
					logger.info("timeout");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			else
			{
				logger.info("Matching issue");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }
	}

	}
}