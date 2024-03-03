package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.AdminAndUser;
import com.epam.sl.AdminAndUserValidationService;
@Component
public class UserSignUp {
	@Autowired
	AdminAndUserValidationService av;
	@Autowired
	UserDetailsUi userDetailsUi;
    Logger logger = LogManager.getLogger(UserSignUp.class);
	Scanner sc = new Scanner(System.in);
	public void userSignUp()
	{
	String userType="user";
	logger.info("enter username");
	String name=sc.next();
	logger.info("enter password");
	String password=sc.next();
	AdminAndUser user=new AdminAndUser(name, password, userType);
	if(av.userSignUp(user))
	{
		logger.info("user added successfully");
		
		userDetailsUi.userDetailsUi();
	}
			
			
		}
	}

