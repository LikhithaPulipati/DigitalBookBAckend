package com.digitalbooks.UserDigitalbooks.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalbooks.UserDigitalbooks.dao.RoleDao;
import com.digitalbooks.UserDigitalbooks.dao.UserDao;
import com.digitalbooks.UserDigitalbooks.entity.Role;
import com.digitalbooks.UserDigitalbooks.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void initRoleAndUser() {

		Role adminRole = new Role();
		adminRole.setRoleName("ADMIN");
		// adminRole.setRoleName(roleDao.f);

		roleDao.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("READER");

		roleDao.save(userRole);

		
		 User adminUser = new User(); adminUser.setUserName("admin123");
		 adminUser.setUserPassword(getEncodedPassword("admin@123"));
		 adminUser.setUserFirstName("admin"); adminUser.setUserLastName("admin");
		 Set<Role> adminRoles = new HashSet<>(); adminRoles.add(adminRole);
		 //adminUser.setRole(adminRoles); userDao.save(adminUser);
		 

	}

	public boolean registerNewUser(User user) {
		Optional<User> ouser = userDao.findById(user.getUserName());
		if (!ouser.isPresent()) {
			System.out.println("user data"+user.toString());
			user.setUserPassword(getEncodedPassword(user.getUserPassword()));
			System.out.println("registerNewUser userPassword"+user.getUserPassword());
			//user.setAllUserSubscriptions(new ArrayList<>());
			/*
			 * Role role = roleDao.findById(user.getUserName()).get(); Set<Role> userRole =
			 * new HashSet<>(); userRole.add(role); user.setRole(userRole); //
			 * adminRole.setRoleName(roleDao.f); roleDao.saveAll(userRole);
			 */
			userDao.save(user);
			System.out.println("User Saved Successfully");
			return true;
		} else {
			System.out.println("no user");
			return false;
		}

		// return userDao.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
