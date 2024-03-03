package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class QuestionLibraryUI {
	 Logger logger = LogManager.getLogger(QuestionLibraryUI.class);
	 @Autowired
	 QuestionLibrarySelection questionLibrarySelection;

	public void questionLibraryUI() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do
		{
			logger.info("choose the Question library operation");
			logger.info("1-AddQuestion");
			logger.info("2-ViewAllQuestions,");
			logger.info("3-ViewQuestionById,");
			logger.info("4-EditQuestion,");
			logger.info("5-DeleteQuestion");
			logger.info("enter 6 for Library type selection");
			 choice = sc.nextInt();
			questionLibrarySelection.createUser(choice);
		}while(choice!=7);
		

	}

}
