package com.email.model;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {
	@Id
	String email;
	LocalDateTime generatedTime;
	LocalDateTime expiryTime;
	int otp;
	public Email(String email, int otp) {
		super();
		this.email = email;
		this.generatedTime=null;
		this.expiryTime=null;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime() {
		this.generatedTime = LocalDateTime.now();
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime() {
		this.expiryTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
	}
	public int getOtp() {
		return otp;
	}
	public int setOtp() {
		return this.otp = generateOTP();
		
	}
	public Email() {
		super();
		
	}
	public int generateOTP() {
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return this.otp = number;
	}
	
	
	

}