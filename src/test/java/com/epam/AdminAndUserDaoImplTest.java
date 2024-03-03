package com.epam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.db.AdminAndUserDaoImpl;
import com.epam.entity.AdminAndUser;

@ExtendWith(MockitoExtension.class)
 class AdminAndUserDaoImplTest {

    @Mock
    EntityManager entityManager;
    @InjectMocks
    AdminAndUserDaoImpl adminAndUserDaoImpl;
    @Mock
    TypedQuery query;
    @Mock
    EntityTransaction transaction;
    @Test
    void getAdminAndUserDetailsTest()
    {
        AdminAndUser admin=new AdminAndUser("admin","pinky","123");
        AdminAndUser user=new AdminAndUser("user","sree","abc");
        List<AdminAndUser> availableData=new ArrayList<>(Arrays.asList(admin,user));
        Mockito.when(entityManager.createQuery("Select t from AdminAndUser t ",AdminAndUser.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(availableData);
        List<AdminAndUser> retrievedData=adminAndUserDaoImpl.getAdminAndUserDetails();
        assertEquals(availableData,retrievedData);
    }
    @Test
    void saveUsersTest()
    {
        AdminAndUser user=new AdminAndUser("user","sree","abc");
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        doNothing().when(entityManager).persist(user);
        AdminAndUser savedUser=adminAndUserDaoImpl.saveUsers(user);
        assertEquals(user,savedUser);

    }


}
