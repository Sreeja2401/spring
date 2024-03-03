package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.AdminAndUser;
import com.epam.sl.AdminAndUserValidationService;
@Component
public class AdminDetailsUi {
	@Autowired
	AdminAndUserValidationService adminValidation;
	@Autowired
	AdminOptionsMenu adminOptionsMenu;
	@Autowired
	AdminDetailsUi adminDetailsUi;
	final Logger logger = LogManager.getLogger(AdminDetailsUi.class);
	Scanner sc = new Scanner(System.in);
	public void adminDetailsUi()
	{

		logger.info("enter admin username:");
		String name = sc.next();
		logger.info("enter password");
		String password = sc.next();
		String userType="admin";
		AdminAndUser admin=new AdminAndUser(userType,name,password);
		if (adminValidation.validateAdminAndUser(admin))
		{
			logger.info("login successfull");
			adminOptionsMenu.adminOptionsMenu();
			
		}
		else
			logger.info("ADMIN AUTHENTICATION FAILED");
		    logger.info("please enter corect details");
		    adminDetailsUi.adminDetailsUi();
	}

}
