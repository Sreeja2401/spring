package com.epam.sl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.db.QuizLibraryOperationsDao;
import com.epam.db.QuizLibraryOperationsDaoImpl;
import com.epam.entity.QuizLibrary;
@Service
public class QuizLibraryOperationsService {
	@Autowired
	QuizLibraryOperationsDao quizLibraryOperationsImpl;

	public QuizLibrary addQuizz(QuizLibrary q) throws DuplicateQuizFoundException {
			if (checkQuizTitle(q.getTitle()).isEmpty()) {
				quizLibraryOperationsImpl.save(q);
				
			}
			else {
				throw new DuplicateQuizFoundException("quiz with given title already exist..... ");
			}
		return q;
	}

	public List<QuizLibrary> viewQuiz() {
		return quizLibraryOperationsImpl.viewAll();
	}

	public List<QuizLibrary> viewQuizByDomain(String domain) throws QuizNotFoundException {

		List<QuizLibrary> availableQuiz = quizLibraryOperationsImpl.viewAll();
		List<QuizLibrary> retrievedQuiz = new ArrayList<>();
		for (QuizLibrary quiz : availableQuiz) {
			if (quiz.getTitle().equals(domain)) {
				retrievedQuiz.add(quiz);
			}
		}
		if (retrievedQuiz.isEmpty()) {
			throw new QuizNotFoundException("quiz does not exist with given domain");
		}
		return retrievedQuiz;
	}

	public boolean editQuiz(String existingDomainName, String updatedDomainName) {

		boolean result = false;
		List<QuizLibrary> availableQuiz = quizLibraryOperationsImpl.viewAll();
		for (QuizLibrary counter : availableQuiz) {
			if (counter.getTitle().equalsIgnoreCase(existingDomainName)) {
				counter.setTitle(updatedDomainName);
				result = true;
			}

			quizLibraryOperationsImpl.update(counter);
		}
		return result;
	}

	public boolean deleteQuiz(String title) {
		boolean result = false;
		List<QuizLibrary> availableQuestions = quizLibraryOperationsImpl.viewAll();
		for (QuizLibrary q : availableQuestions) {
			if (q.getTitle().equals(title)) {
				
				quizLibraryOperationsImpl.delete(q.getId());
				result = true;
			}

		}
		return result;

	}

	public Optional<QuizLibrary> checkQuizTitle(String title) {
		return quizLibraryOperationsImpl.viewAll().stream().filter(q -> q.getTitle().equalsIgnoreCase(title)).findAny();


	}
}
