package com.digitalbooks.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.digitalbooks.exception.BookMicroserviceException;
import com.digitalbooks.model.Book;

public interface BookService {

	Collection<Book> findAll();

	Optional<Book> findById(Long id);

	Book save(Book book);

	Book update(Book book) throws BookMicroserviceException;

	void deleteById(Long id);

	List<Book> saveAll(List<Book> bookList);
	// List<Optional<Book>> findBooksWithUser(String username);

}
