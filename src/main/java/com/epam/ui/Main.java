package com.epam.ui;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {
	
	@Autowired
	UserSelection userSelection;
	public  void main() 
	{
	    Logger logger = LogManager.getLogger(Main.class);
		logger.info("welcome to Quiz Application");
		
		int choiceForRoleSelection;
		do
		{
			logger.info("Enter user type :");
			logger.info("  1.Admin   ");
			logger.info("  2.User  ");
			logger.info("  3.exit   ");
		Scanner sc = new Scanner(System.in);
		choiceForRoleSelection = sc.nextInt();
		userSelection.createUser(choiceForRoleSelection);
		}while(choiceForRoleSelection!=3);
		
			
	}
}