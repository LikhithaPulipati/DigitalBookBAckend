package com.digitalbooks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.exception.BookMicroserviceException;
import com.digitalbooks.model.Book;
import com.digitalbooks.repository.BookRepository;

@RestController
@RequestMapping("/api/v1/digitalbooks")
@CrossOrigin()
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/readbooks")
	public List<Book> getAllBooks() {

		List<Book> allbooks = bookRepository.findAll();
		return allbooks;
	}

	@GetMapping("/readSubscribedbooks/{id}")
	public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id,
			@RequestParam(name = "bookTitle", required = false) String bookTile,
			@RequestParam(name = "subscription", required = false) boolean subscriptionToggle) {

		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent() && book.get().isActive()) {
			book.get().setSubscription(subscriptionToggle);
			return ResponseEntity.ok(book);
		}

		else
			throw new BookMicroserviceException("Book is Currently blocked");
	}

	@GetMapping("/readbooksByUser/{userName}")
	public List<Book> getBookByIdByAUser(@PathVariable String userName) {
		List<Book> books = new ArrayList<>();
		books = bookRepository.findByAuthor(userName);
		return books;
	}

	@GetMapping("/readbooks/{id}")
	public Book getBookById(@PathVariable Long id) {
		Book book = bookRepository.findOneById(id);

		return book;
	}

	@GetMapping("/subscribeToBookById/{id}")
	public ResponseEntity<Book> subcribeBookById(@PathVariable Long id) {
		Book book = bookRepository.findOneById(id);

		return ResponseEntity.ok(book);
	}

	@GetMapping("/subscribeToBook/{id}")
	public ResponseEntity<Book> getBookByAUser(@PathVariable Long id) {

		Book book = bookRepository.findOneById(id);
		//System.out.println(book.isActive());

		if (book != null && !book.isSubscription()) {
			book.setSubscription(true);
		}
		// book.setActive(false);
		return ResponseEntity.ok(book);
	}

	@PostMapping("/createbook")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/updatebook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		Optional<Book> book = bookRepository.findById(id);
		Book book1 = book.get();

		book1.setTitle(bookDetails.getTitle());
		book1.setCategory(bookDetails.getCategory());
		book1.setLogoImage(bookDetails.getLogoImage());
		book1.setPrice(bookDetails.getPrice());
		book1.setAuthor(bookDetails.getAuthor());
		book1.setPublisher(bookDetails.getPublisher());
		book1.setPublishedDate(bookDetails.getPublishedDate());
		book1.setActive(bookDetails.isActive());
		book1.setContent(bookDetails.getCategory());

		Book updatedBook = bookRepository.save(book1);
		return ResponseEntity.ok(updatedBook);

	}

	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);

		bookRepository.delete(book.get());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/blockBook/{authorId}/{bookId}")
	public Book blockBook(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		Book book1 = book.get();
		if (book1.getAuthor_id().equals(authorId)) {
			book1.setActive(false);
			bookRepository.save(book1);
		} else {
			return new Book();
		}

		return book1;

	}

	@PutMapping("/unblockBook/{authorId}/{bookId}")
	public Book unblockBook(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		Book book1 = book.get();
		if (book1.getAuthor_id().equals(authorId)) {
			book1.setActive(true);
			bookRepository.save(book1);
		} else {
			return new Book();
		}

		return book1;

	}
}
