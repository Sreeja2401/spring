package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserDetailsUi {
	 Logger logger = LogManager.getLogger(UserDetailsUi.class);
	Scanner sc = new Scanner(System.in);
	@Autowired
	UserSignUp userSignUp;
	@Autowired
	UserSignIn userSignIn;
	@Autowired
	UserDetailsUi userDetailsUi;
	public void userDetailsUi() {
		logger.info("1-user sign Up");
		logger.info("2-user sign In");
		logger.info("press 1 to create a new account or 2 to login into existing accout");
		int choice = sc.nextInt();
		if (choice == 1) {
			
			userSignUp.userSignUp();
		} else if (choice == 2) {
			
			userSignIn.userSignIn();
		} else {
			logger.info("please enter correct option !!");
			userDetailsUi.userDetailsUi();
		}
	}
}
