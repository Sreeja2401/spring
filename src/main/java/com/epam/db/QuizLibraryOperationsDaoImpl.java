package com.epam.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.epam.entity.QuizLibrary;
@Repository
public class QuizLibraryOperationsDaoImpl implements QuizLibraryOperationsDao {

	EntityManagerFactory emf;
	EntityManager em;
	public QuizLibraryOperationsDaoImpl()
	{
		emf=Persistence.createEntityManagerFactory("sreeja");
		em=emf.createEntityManager();
	}
	@Override
	public QuizLibrary save(QuizLibrary t) {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();

		return t;
	}
	@Override
	public List<QuizLibrary> viewAll() {
		List<QuizLibrary> availableQuestions;
			availableQuestions = em.createQuery("Select t from QuizLibrary t").getResultList();
		return availableQuestions;
	}


	@Override
	public QuizLibrary update(QuizLibrary q) {
			em.getTransaction().begin();
			em.persist(q);
			em.getTransaction().commit();
		return q;
	}

	@Override
	public boolean delete(int id) {
		em.getTransaction().begin();
		em.remove(em.find(QuizLibrary.class, id));
		em.getTransaction().commit();
		return true;

	}

}
