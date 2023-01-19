package com.digitalbooks.UserDigitalbooks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalbooks.UserDigitalbooks.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
}
