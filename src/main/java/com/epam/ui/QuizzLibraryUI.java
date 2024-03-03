package com.epam.ui;


import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizzLibraryUI {
	@Autowired
	QuizLibrarySelection quizLibrarySelection;
	public void quizzLibraryUI() {
		Scanner sc = new Scanner(System.in);
		final Logger logger = LogManager.getLogger(QuizzLibraryUI.class);
		int choiceForQuizzLibrary;
		do
		{
			logger.info("choose the Quizz library operation");
			logger.info(" 1-Add Questions to Quizz library ");
			logger.info(" 2-View Quizz library ");
			logger.info(" 3-View Quizz library by domain");
			logger.info(" 4-Edit Questions in Quizz Library ,");
			logger.info(" 5-Delete Quizz Library");
			logger.info("enter 6 for Library type selection");
			 choiceForQuizzLibrary = sc.nextInt();
			quizLibrarySelection.createUser(choiceForQuizzLibrary);
			

	}while(choiceForQuizzLibrary!=7);
}
}
