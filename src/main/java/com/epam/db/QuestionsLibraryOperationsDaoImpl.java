package com.epam.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.entity.QuestionsLibrary;

@Repository
public class QuestionsLibraryOperationsDaoImpl implements QuestionsLibraryOperationsDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sreeja");
	EntityManager em = emf.createEntityManager();

	public QuestionsLibrary save(QuestionsLibrary q) {
		em.getTransaction().begin();
		em.persist(q);
		em.getTransaction().commit();
		return q;
	}

	public boolean delete(int questionId) {
		em.getTransaction().begin();
		em.remove(em.find(QuestionsLibrary.class, questionId));
		em.getTransaction().commit();
		return true;
	}

	
	public List<QuestionsLibrary> viewAll() {
		TypedQuery<QuestionsLibrary> query = em.createQuery("select q from QuestionsLibrary q", QuestionsLibrary.class);
		 return query.getResultList();
		 
	}

	public QuestionsLibrary viewById(int id) {
		return em.find(QuestionsLibrary.class, id);

	}

	public QuestionsLibrary update(QuestionsLibrary question) {
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		return question;
	}

}
