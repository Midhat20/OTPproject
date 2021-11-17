package com.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.email.dao.EmailDao;
import com.email.model.Email;
import com.email.service.EmailService;

@SpringBootTest
class EmailapiApplicationTests {
	
	@Autowired
    private EmailDao emaildao;
    private EmailService service;
	
	@Test
	void contextLoads() { 
					
	}
	
	
	@Test
	void checkOtpTest() {
		
		Email email=new Email();
		int actOTP=email.generateOTP();
		String actual=Integer.toString(actOTP);
		assertTrue(actual.length()==6);
	}
	
	
	@Test
	void checkIfObjectSaved()
	{
		Email em=new Email();
		em.setEmail("midhatfatima11@gmail.com");
		em.setGeneratedTime();
		em.setExpiryTime();
		em.setOtp();
		emaildao.save(em);
	    assertNotNull(em, "Saved email object");
		}
	
	
	public boolean checkEmail(String email)
	{
		String regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(email);
		return matcher.matches();
	}
	
		
	@Test
	void checkEmailTest() {
	boolean expected=true;
	boolean actual=checkEmail("midhatfatima711@gmail.com");
	assertEquals(expected, actual);
			
	}
	
	

}
