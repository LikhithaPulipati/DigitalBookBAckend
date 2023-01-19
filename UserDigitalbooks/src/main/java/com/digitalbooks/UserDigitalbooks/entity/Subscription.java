package com.digitalbooks.UserDigitalbooks.entity;

import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookId;
	private String userName;
	private LocalDate subscribedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public LocalDate getSubscribedDate() {
		return subscribedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSubscribedDate(LocalDate subscribedDate) {
		this.subscribedDate = subscribedDate;
	}

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(Long id, Long bookId, String userName, LocalDate subscribedDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userName = userName;
		this.subscribedDate = subscribedDate;
	}

	public Subscription(Long bookId2, LocalDate now) {
		// TODO Auto-generated constructor stub
		super();

		this.bookId = bookId2;
		this.subscribedDate = now;
	}

	public static class builder {
		// TODO Auto-generated method stub

	}

}
