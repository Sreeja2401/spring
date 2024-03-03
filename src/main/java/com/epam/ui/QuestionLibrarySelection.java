package com.epam.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.entity.QuestionsLibrary;
import com.epam.sl.QuestionsLibraryOperationsService;
@Component
public class QuestionLibrarySelection {
	
	static QuestionsLibraryOperationsService questionsLibraryOperations;
	@Autowired
	public void setQuestionsLibraryOperationsService(QuestionsLibraryOperationsService questionsLibraryOperations) {
		QuestionLibrarySelection.questionsLibraryOperations = questionsLibraryOperations;
	}
	static AdminOptionsMenu adminOptionsMenu;
	public void setAdminOptionsMenu(AdminOptionsMenu adminOptionsMenu) {
		QuestionLibrarySelection.adminOptionsMenu = adminOptionsMenu;
	}
	
	 static Logger logger = LogManager.getLogger(QuestionLibrarySelection.class);

	private static final Map<Integer, Selection> options;
	static {
		final Map<Integer, Selection> userOption = new HashMap<>();
		 Scanner sc = new Scanner(System.in);
		userOption.put(1, () -> {
			logger.info("enter no questions that to be added");
			int noOfQuestions = sc.nextInt();
			for (int c = 1; c <= noOfQuestions; c++) {
				logger.info("enter question details:");
				sc.nextLine();
				logger.info("enter question title");
				String questionTitle = sc.nextLine();
				logger.info("enter no of options to be entered");
				int noOfOptions = sc.nextInt();
				List<String> listOfOptions = new ArrayList<>();
				for (int i = 1; i <= noOfOptions; i++) {
					String format=String.format("enter option %d ",i);
					logger.info(format);
					listOfOptions.add(sc.next());
				}
				sc.nextLine();
				logger.info("enter difficulty level:");
				String difficultyLevel = sc.next();
				logger.info("enter tagging topics:");
				String taggingTopics = sc.next();
				logger.info("enter correct answer:");
				String answers = sc.next();
				QuestionsLibrary q=new QuestionsLibrary( questionTitle, listOfOptions, difficultyLevel,
						taggingTopics, answers);
				try{
				 questionsLibraryOperations.addQuestion(q);
					logger.info(" question added successfully");
				} catch(DuplicateQuestionFoundException e)
				{
					logger.info(e);
				}
			}
		});
		userOption.put(2, () -> {
			if (questionsLibraryOperations.viewAllQuestion().isEmpty()) {
				logger.info("question library is empty");
			} else {
				logger.info(questionsLibraryOperations.viewAllQuestion());
			}
		});
		userOption.put(3, () -> {

			logger.info("enter id of the question");
			int id = sc.nextInt();
			try
			{
				logger.info(questionsLibraryOperations.viewQuestionById(id));
			}
			catch (QuestionIdNotFoundException e)
			{
				logger.info(e);
			}

		});
		userOption.put(4, () -> {
			logger.info("enter question id that to be edited");
			int id = sc.nextInt();
			try
			{
			if(questionsLibraryOperations.viewQuestionById(id)!=null)
			{
			logger.info("enter no of fields to be edited");
			int noOfFields = sc.nextInt();
			logger.info("available fields are:");
			logger.info("questionTitle,options,difficultylevel,taggingtopics,answer");
			for (int i = 1; i <= noOfFields; i++) {
				logger.info("enter field that to be edited");
				String existingValue = sc.next();
				logger.info("enter updated value");
				String updatedValue = sc.next();
				questionsLibraryOperations.updateQuestion(id, existingValue, updatedValue);
				logger.info("updated successfully");
			}
			}
			}catch (QuestionIdNotFoundException e) {
					logger.info(e);
				}

			});
		userOption.put(5, () -> {
			logger.info("enter id of question that to be deleted");
			int questionId = sc.nextInt();
			try
			{
			if(questionsLibraryOperations.viewQuestionById(questionId)!=null)
			{
			questionsLibraryOperations.deleteQuestion(questionId);
				logger.info("deleted successfully");
			}
			}
			catch (QuestionIdNotFoundException e)
			{
				logger.info(e);
			}
		});

		userOption.put(6, () -> {


			adminOptionsMenu.adminOptionsMenu();

		});
		options = Collections.unmodifiableMap(userOption);
	}

	public void createUser(int userType) {
		Selection command = options.get(userType);

		if (command == null) {
			logger.info("choose the valid option !!");
		}
		//try {
			assert command != null;
			command.select();
//		} catch (NullPointerException e) {
//			logger.info(e);
//		}
	}
}
