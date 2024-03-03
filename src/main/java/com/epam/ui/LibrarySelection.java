package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class LibrarySelection {
    Logger logger = LogManager.getLogger( LibrarySelection.class);
    static QuestionLibraryUI questionLibraryUI;
    @Autowired
	public void setQuestionLibraryUI(QuestionLibraryUI questionLibraryUI) {
    	LibrarySelection.questionLibraryUI = questionLibraryUI;
    	
	}
   static QuizzLibraryUI quizzLibraryUI;
    @Autowired
    public void setQuizzLibraryUI(QuizzLibraryUI quizzLibraryUI) {
    	LibrarySelection.quizzLibraryUI = quizzLibraryUI;

	}
    
	private static final Map<Integer, Selection> UserType;
	static {
		UserType = Map.of(1, () -> {
			questionLibraryUI.questionLibraryUI();
		}, 2, () -> {
			quizzLibraryUI.quizzLibraryUI();

		}, 3, () -> System.exit(0));
	}
	public void createUser(int userType) {
		Selection command = UserType.get(userType);
		
		if (command == null)
		{
			logger.info("choose the valid option !!");
		}
		try{
			assert command != null;
			command.select();
		}
		catch(NullPointerException e)
		{
			logger.info(e);
		}
	}
}

