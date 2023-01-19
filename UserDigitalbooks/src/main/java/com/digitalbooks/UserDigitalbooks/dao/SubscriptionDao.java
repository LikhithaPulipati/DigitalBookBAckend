package com.digitalbooks.UserDigitalbooks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalbooks.UserDigitalbooks.entity.Subscription;



@Repository
public interface SubscriptionDao extends JpaRepository<Subscription, Long> {

	public List<Subscription> findAllByUserName(String userName);

}
