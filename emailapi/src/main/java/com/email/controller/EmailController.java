package com.email.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.dao.EmailDao;
import com.email.model.Email;
import com.email.service.EmailService;
import com.email.service.Services;

@RestController
public class EmailController {
	
    @Autowired
    private EmailDao emaildao;
	
//	
//	@RequestMapping("/welcome")
//	public String welcome() {
//		return "hi";
//	}
//	
//	@RequestMapping(value="/sendemail",method=RequestMethod.POST)
//	public ResponseEntity<?> sendEmail(@RequestBody EmailOtp request)
//	{
//		request.setOtp();
//		System.out.println(request);
//		emaildao.save(request);
//     boolean result=EmailService.sendEmail( String.valueOf(request.getOtp()) , "Midhat OTP", request.getEmail());
//	if(result)
//	{
//		 return ResponseEntity.ok("successful");
//	} else
//	{
//		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email not sent");
//	}
    
    
     @Autowired
	 private JavaMailSender javaMailSender;
	
	
	
	@Autowired
	private Services services;

	
	@CrossOrigin
	@GetMapping("/email")
	public List<Email> getAllEmail(){
		return services.getAllEmail();
	}
	
	@CrossOrigin
	@GetMapping("/email/{email}")
	public Email getByEmail(@PathVariable String email ) {
	
		return services.getByEmail(email);
		
	}

	@CrossOrigin
	@PostMapping("/email")
	public ResponseEntity<HttpStatus> addEmail(@RequestBody Email email ) {
		
		email.setGeneratedTime();
		email.setExpiryTime();
		email.setOtp();
		return this.services.addEmail(email);
	}
	
	@CrossOrigin
	@PostMapping("/otp-validate")
	public ResponseEntity<HttpStatus> otpValidate(@RequestBody Email eo) 
	{
		return services.OtpValidate(eo);
	}
     
}
