package com.epam;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.db.AdminAndUserDao;
import com.epam.entity.AdminAndUser;
import com.epam.sl.AdminAndUserValidationService;

@ExtendWith(MockitoExtension.class)
 class AdminAndUserValidationServiceTest {
    @Mock
    AdminAndUserDao adminAndUserDao;
    @InjectMocks
    AdminAndUserValidationService adminAndUserValidationService;
   List<AdminAndUser>availableData;
        @BeforeEach
        public void setData()
       {
           AdminAndUser admin=new AdminAndUser("admin","pinky","123");
           AdminAndUser user=new AdminAndUser("user","sree","abc");
           availableData=new ArrayList<>(Arrays.asList(admin,user));
       }
    @Test
     void validateAdminTest()
    {
        AdminAndUser admin=new AdminAndUser("admin","pinky","123");
        Mockito.when( adminAndUserDao.getAdminAndUserDetails()).thenReturn(availableData);
        boolean result =adminAndUserValidationService.validateAdminAndUser(admin);
        assertTrue(result);
    }
    @Test
     void validateAdminWithInvalidCredentials()
    {
        AdminAndUser admin=new AdminAndUser("admin","sree","123");
        Mockito.when( adminAndUserDao.getAdminAndUserDetails()).thenReturn(availableData);
        boolean result =adminAndUserValidationService.validateAdminAndUser(admin);
        assertFalse(result);
    }
    @Test
     void validateAdminWithInvalidAdminData()
    {
        AdminAndUser admin=new AdminAndUser("admin","pinky","456");
        Mockito.when( adminAndUserDao.getAdminAndUserDetails()).thenReturn(availableData);
        boolean result =adminAndUserValidationService.validateAdminAndUser(admin);
        assertFalse(result);
    }
    @Test
     void userSignUpTest()
    {
        AdminAndUser user=new AdminAndUser("user","pandu","1234");
        Mockito.when(adminAndUserDao.saveUsers(user)).thenReturn(user);
        boolean result =adminAndUserValidationService.userSignUp(user);
        assertTrue(result);
    }
    @Test
     void userSignInWithNotExistingUser()
    {
        AdminAndUser user=new AdminAndUser("user","pandu","1234");
        Mockito.when(adminAndUserDao.getAdminAndUserDetails()).thenReturn(availableData);
        boolean result =adminAndUserValidationService.validateAdminAndUser(user);
        assertFalse(result);
    }
    @Test
     void userSignInWithExistingUser()
    {
        AdminAndUser user=new AdminAndUser("user","sree","abc");
        Mockito.when(adminAndUserDao.getAdminAndUserDetails()).thenReturn(availableData);
        boolean result =adminAndUserValidationService.validateAdminAndUser(user);
        assertTrue(result);
    }




}
