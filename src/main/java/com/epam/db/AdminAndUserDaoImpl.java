package com.epam.db;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.entity.AdminAndUser;
@Repository
public class AdminAndUserDaoImpl implements AdminAndUserDao {
	
	
	EntityManagerFactory emf;
	EntityManager em;
	@PostConstruct
	public void init()
	{
		System.out.println("in init method");
		createConnection();
	}
	public void createConnection()
	{
		emf=Persistence.createEntityManagerFactory("sreeja");
		 em=emf.createEntityManager();
	}
	public List<AdminAndUser> getAdminAndUserDetails()
	{
		List<AdminAndUser> availableAdminsAndUsers;
		availableAdminsAndUsers=em.createQuery("Select t from AdminAndUser t ",AdminAndUser.class).getResultList();
		return availableAdminsAndUsers ;
	}
   
	public AdminAndUser saveUsers(AdminAndUser t) {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			return t;
	}
	public void destroyConnection()
	{
		em.close();
	}
    @PreDestroy
    public void destroy()
    {
    	System.out.println("in destroy method");
    	destroyConnection();
    }
	}


