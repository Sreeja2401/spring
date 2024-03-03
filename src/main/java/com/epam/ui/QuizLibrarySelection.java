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

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.entity.QuestionsLibrary;
import com.epam.entity.QuizLibrary;
import com.epam.sl.QuestionsLibraryOperationsService;
import com.epam.sl.QuizLibraryOperationsService;
@Component
public class QuizLibrarySelection {
  static Logger logger = LogManager.getLogger( QuizLibrarySelection.class);
  static QuizLibraryOperationsService quizLibraryOperations;

    @Autowired
	public void setQuizLibraryOperations(QuizLibraryOperationsService quizLibraryOperations) {
	  QuizLibrarySelection.quizLibraryOperations = quizLibraryOperations;
	}
   static QuestionsLibraryOperationsService questionsLibraryOperationsService;
   @Autowired
	public void setQuestionsLibraryOperations(QuestionsLibraryOperationsService questionsLibraryOperationsService) {
	  QuizLibrarySelection.questionsLibraryOperationsService = questionsLibraryOperationsService;
	}
   static AdminOptionsMenu adminOptionsMenu;
   @Autowired
  	public void setAdminOptionsMenu(AdminOptionsMenu adminOptionsMenu) {
  	  QuizLibrarySelection.adminOptionsMenu = adminOptionsMenu;
  	}
	private static final Map<Integer, Selection> options;
	static {
		final Map<Integer,Selection> userOption = new HashMap<>();
		Scanner sc = new Scanner(System.in);
	userOption.put(1, () -> {
				try {
					logger.info("enter domain");
					String domain = sc.next();
					logger.info("enter no of questions");
					int numberOfQuestions = sc.nextInt();
					logger.info("enter the question id's to be added into quiz");
					List<Integer> listOfQuestionNumbers = new ArrayList<>();
					for (int i = 0; i < numberOfQuestions; i++) {
						listOfQuestionNumbers.add(sc.nextInt());
					}
					
					List<QuestionsLibrary> availableQuestions = questionsLibraryOperationsService.viewAllQuestion();
					List<Integer> availableQuestionNumbers = availableQuestions.stream().map(e -> e.getQuestionNumber()).toList();
					
					List<Integer> questionsToBeAddedToQuiz = new ArrayList<>();
					for (Integer l : listOfQuestionNumbers) {
						if (availableQuestionNumbers.contains(l)) {
							questionsToBeAddedToQuiz.add(l);
						}
						else 
						{
							String format=String.format(" the given question number %d is not present in available questions",l);
							logger.info(format);
						}
					}
					List<QuestionsLibrary> quizList = new ArrayList<>();
					for (Integer i : questionsToBeAddedToQuiz) {
						quizList.add(questionsLibraryOperationsService.viewQuestionById(i));
					}
			
					logger.info("enter no of marks ");
					int marks = sc.nextInt();
					QuizLibrary quiz=new QuizLibrary(domain,marks,quizList);
					  quizLibraryOperations.addQuizz(quiz) ;
						logger.info("quiz added successfully");

				}
				catch (QuestionIdNotFoundException | DuplicateQuizFoundException q)
				{
					logger.info(q);
				}
			}
	);
	userOption.put(2, () -> {
	if(quizLibraryOperations.viewQuiz().isEmpty())
	{
		logger.info("quiz library is empty");
	}
	else
	{
		logger.info(quizLibraryOperations.viewQuiz());
	}
	});

		userOption.put(3, () -> {
			logger.info("enter the domain that to be viewed");
			String domain= sc.next();
			try {
				logger.info(quizLibraryOperations.viewQuizByDomain(domain));
			}
			catch (QuizNotFoundException e)
			{
				logger.info(e);
			}
		});
	userOption.put(4, () -> {

		logger.info("enter the existing domain name");
		String existingDomainName = sc.next();
		logger.info("enter the updated domain name");
		String updatedDomainName = sc.next();
		if(quizLibraryOperations.editQuiz(existingDomainName, updatedDomainName))
		{
			logger.info("quiz updated successfully");
		}
		else
		{
			logger.info("domain not exist");
		}
	});
	userOption.put(5, () -> {
	logger.info("enter the title of quiz that to be deleted");

	String title = sc.next();

	if(quizLibraryOperations.deleteQuiz(title))
	{
		logger.info("quiz deleted successfully");
	}
	else {
		logger.info("quiz not found");
	}
	});
	
	userOption.put(6, () -> {
		
		adminOptionsMenu.adminOptionsMenu();

	}
	);
	options = Collections.unmodifiableMap(userOption);
	}
	public void createUser(int userType) {
		Selection command = options.get(userType);
		
		if (command == null)
		{
			logger.info("choose the valid option !!");
		}
		//try{
			assert command != null;
			command.select();
		//}
//		catch(NullPointerException e)
//		{
//			logger.info(e);
//		}
	}
}


