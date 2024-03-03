package com.epam.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSelection {
	
	static AdminDetailsUi adminDetailsUi;

	@Autowired
	public void setAdminDetailsUi(AdminDetailsUi adminDetailsUi) {
		UserSelection.adminDetailsUi = adminDetailsUi;
	}

	Logger logger = LogManager.getLogger(UserSelection.class);
	private static final Map<Integer, Selection> UserType;
	static {
		final Map<Integer, Selection> userOption = new HashMap<>();
		userOption.put(1, () -> {

			adminDetailsUi.adminDetailsUi();

		});
		userOption.put(2, () -> {
			UserDetailsUi userDetailsUi = new UserDetailsUi();
			userDetailsUi.userDetailsUi();

		});
		userOption.put(3, () -> System.exit(0));
		UserType = Collections.unmodifiableMap(userOption);
	}

	public void createUser(int userType) {
		Selection command = UserType.get(userType);

		if (command == null) {
			logger.info("choose the valid option !!");
		}
		//try {
			assert command != null;
			command.select();
		//} catch (NullPointerException e) {
			//logger.info(e);
		//}
	}

}