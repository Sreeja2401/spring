package com.epam.sl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.DuplicateQuestionFoundException;
import com.epam.customexceptions.QuestionIdNotFoundException;
import com.epam.db.QuestionsLibraryOperationsDao;
import com.epam.db.QuestionsLibraryOperationsDaoImpl;
import com.epam.entity.QuestionsLibrary;
@Service
public class QuestionsLibraryOperationsService {
	
	@Autowired
	QuestionsLibraryOperationsDao qlo;

	public QuestionsLibrary addQuestion(QuestionsLibrary q) throws DuplicateQuestionFoundException {
		List<QuestionsLibrary> availableQuestions = qlo.viewAll();
		List<String> listOfTitles = new ArrayList<>();
		for (QuestionsLibrary question : availableQuestions) {
			listOfTitles.add(question.getQuestionTitle());
		}
		if (listOfTitles.contains(q.getQuestionTitle())) {
			throw new DuplicateQuestionFoundException("question with given title already exist");
		}
		qlo.save(q);

		return q;
	}
	

	public void deleteQuestion(int questionId)throws QuestionIdNotFoundException
		{
			if (qlo.viewById(questionId) != null)
			{
				qlo.delete(questionId);
			}

		    else
			{
				throw new QuestionIdNotFoundException("id not found");
			}
	   }

	public List<QuestionsLibrary> viewAllQuestion() {
		return qlo.viewAll();
	}

	public QuestionsLibrary viewQuestionById(int id) throws QuestionIdNotFoundException {
		if (qlo.viewById(id) != null) {
			return qlo.viewById(id);
		}
		throw new QuestionIdNotFoundException("id not found");
	}


	public boolean updateQuestion(int id, String existingField, String updatedValue){
		boolean result = false;
		QuestionsLibrary question = qlo.viewById(id);
		if (existingField.equalsIgnoreCase("questionTitle")) {
			question.setQuestionTitle(updatedValue);
			result = true;
		}
		if (existingField.equalsIgnoreCase("options")) {
            List<String> newOptions=new ArrayList<>(question.getOptions());
			newOptions.add(updatedValue);
			question.setOptions(newOptions);
			result = true;
		}
		if (existingField.equalsIgnoreCase("difficultylevel")) {
			question.setDifficultyLevel(updatedValue);
			result = true;
		}
		if (existingField.equalsIgnoreCase("taggingtopic")) {
			question.setTaggingTopics(updatedValue);
			result = true;
		}
		if (existingField.equalsIgnoreCase("answer")) {
			question.setAnswers(updatedValue);
			result = true;
		}
		qlo.update(question);

		return result;
	}




}
