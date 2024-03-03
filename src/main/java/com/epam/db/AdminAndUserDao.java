package com.epam.db;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.epam.entity.AdminAndUser;

public interface AdminAndUserDao {
   public List<AdminAndUser> getAdminAndUserDetails();
   public AdminAndUser saveUsers(AdminAndUser t);
}
