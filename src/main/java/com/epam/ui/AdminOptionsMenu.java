package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AdminOptionsMenu {
	 Logger logger = LogManager.getLogger(AdminOptionsMenu.class);
	 @Autowired
	 LibrarySelection librarySelection;
	public void adminOptionsMenu() {
		
		int choiceForRoleSelection;
		do
		{
			logger.info("choose one form below options:");
			logger.info("       1-QuestionLibrary     ");
			logger.info("       2-QuizzLibrary     ");
			logger.info("       3-exit ");
		Scanner sc = new Scanner(System.in);
		choiceForRoleSelection = sc.nextInt();
		librarySelection.createUser(choiceForRoleSelection);
	
		}while(choiceForRoleSelection<Integer.MAX_VALUE);
	}

}
