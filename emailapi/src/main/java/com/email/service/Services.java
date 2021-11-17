package com.email.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.email.model.Email;


public interface Services {

	    List<Email> getAllEmail();
	    Email getByEmail(String email) ;
   		ResponseEntity<HttpStatus> addEmail(Email email) ;
		ResponseEntity<HttpStatus> OtpValidate(Email eo);
	
	
}
