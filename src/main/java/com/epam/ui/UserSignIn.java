package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.AdminAndUser;
import com.epam.sl.AdminAndUserValidationService;
@Component
public class UserSignIn {
	@Autowired
	AdminAndUserValidationService av;
	@Autowired
	UserDetailsUi userDetailsUi;
	 Logger logger = LogManager.getLogger(UserSignIn.class);
	Scanner sc = new Scanner(System.in);
	public void userSignIn()
	{
		logger.info("enter username");
		String name=sc.next();
		logger.info("enter password");
		String password=sc.next();
		String usertype="user";
		
		AdminAndUser user=new AdminAndUser(name,password,usertype);
		if(av.validateAdminAndUser(user))
		{
			logger.info(" user authenticated successfully");
		}
		else
		{
			logger.info("user does not exist ");
			logger.info("if you want create a account please sign up first");
			
			userDetailsUi.userDetailsUi();
		}
	}

	
}
