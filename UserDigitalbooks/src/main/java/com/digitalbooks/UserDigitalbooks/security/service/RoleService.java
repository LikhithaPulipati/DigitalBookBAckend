package com.digitalbooks.UserDigitalbooks.security.service;

import com.digitalbooks.UserDigitalbooks.dao.RoleDao;
import com.digitalbooks.UserDigitalbooks.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}
}
