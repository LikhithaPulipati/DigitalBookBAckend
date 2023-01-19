package com.digitalbooks.UserDigitalbooks.dao;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.digitalbooks.UserDigitalbooks.entity.Book;
import com.digitalbooks.UserDigitalbooks.entity.Subscription;




public class BookSubscription {

	private final ResponseEntity<Optional<Book>> book;
	private final Subscription subscription;

	public BookSubscription(ResponseEntity<Optional<Book>> book2, Subscription subscription) {
		super();
		this.book = book2;
		this.subscription = subscription;
	}

	public ResponseEntity<Optional<Book>> getBook() {
		return book;
	}

	public Subscription getSubscription() {
		return subscription;
	}

}
