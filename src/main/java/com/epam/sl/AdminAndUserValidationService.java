package com.epam.sl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.epam.db.AdminAndUserDao;
import com.epam.db.AdminAndUserDaoImpl;
import com.epam.entity.AdminAndUser;

@Service
public class AdminAndUserValidationService {
	@Autowired
	AdminAndUserDao au;
	
	public boolean validateAdminAndUser(AdminAndUser role) {
		boolean result=false;

		List<AdminAndUser> availableData = au.getAdminAndUserDetails();
			for (AdminAndUser a : availableData) {
				if (a.getUserType().equals(role.getUserType()) && a.getUserName().equals(role.getUserName()) && a.getPassword().equals(role.getPassword())
				) {
					result = true;

					break;
				}
			}
			return result;
		}


	public boolean userSignUp(AdminAndUser user) {
			au.saveUsers(user);
			return true;
		}
	}

