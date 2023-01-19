package com.digitalbooks.service;

import java.util.List;
import java.util.Optional;

import com.digitalbooks.exception.BookMicroserviceException;
import com.digitalbooks.model.Book;
import com.digitalbooks.repository.BookRepository;

public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository = null;

	@Override
	public List<Book> findAll() {

		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book save(Book book) {

		return bookRepository.save(book);
	}

	@Override
	public Book update(Book book) throws BookMicroserviceException {

		if (book.getTitle() == null)
			throw new BookMicroserviceException("Book ID not found, ID is required for update the data");

		return bookRepository.saveAndFlush(book);
	}

	@Override
	public List<Book> saveAll(List<Book> bookList) {

		return bookRepository.saveAll(bookList);
	}

	@Override
	public Optional<Book> findById(Long id) {

		return bookRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {

		bookRepository.deleteById(id);
	}

}
