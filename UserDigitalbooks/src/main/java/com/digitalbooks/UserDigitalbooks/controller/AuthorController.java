package com.digitalbooks.UserDigitalbooks.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.UserDigitalbooks.Exception.BookUserMicroServiceException;
import com.digitalbooks.UserDigitalbooks.clientPayload.BookClient;
import com.digitalbooks.UserDigitalbooks.dao.UserDao;
import com.digitalbooks.UserDigitalbooks.entity.Book;
import com.digitalbooks.UserDigitalbooks.entity.User;
import com.digitalbooks.UserDigitalbooks.security.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "user_security_scheme")
@CrossOrigin()
public class AuthorController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookClient bookClient;

	@Autowired
	private UserDao userDao;

	@PostConstruct
	public void initRoleAndUser() {
		userService.initRoleAndUser();
	}

	@GetMapping("/getBooks/{username}")
	public List<Book> userBookSubscriotion(@PathVariable String username) {
		System.out.println("Data" + username);
		if (userDao.findById(username) != null) {
			System.out.println(username);
			return bookClient.getBookByIdByAUser(username);
		}

		return null;
	}

	@PostMapping("/author/createbook/{authorId}")
	public Book createBook(@PathVariable String authorId, @RequestBody Book book) {
		System.out.println(book.toString());
		Optional<User> user = userDao.findById(authorId);
		System.out.println(user.toString());
		if (user == null)
			throw new BookUserMicroServiceException("User is not valid");
		else {
		}
		return bookClient.createBook(book);
	}

	@PutMapping("/author/updatebook/{authorId}/{bookId}")
	public Book updateBook(@PathVariable(name = "authorId") String authorId, @PathVariable(name = "bookId") Long bookId,
			@RequestBody Book book) {
		if (userDao.findById(authorId) != null) {
			return bookClient.updateBook(bookId, book);
		}
		return null;
	}

	@GetMapping("/getBooks")
	public List<Book> getAllBooks() {
		return bookClient.getAllBooks();
	}

	@PutMapping("/author/blockBook/{authorId}/{bookId}")
	public String blockBook(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId) throws BookUserMicroServiceException {
		Optional<User> user = userDao.findById(authorId);
		if (user.get().getRole().equals("ADMIN")) {
			Book newBook = bookClient.blockBookAUthor(authorId, bookId);
			if (newBook.getId() != 0) {
				return "Book got blocked ";
			} else {
				return "Book does not belong to this author";
			}
		} else {
			return "Only Authors can block";
		}
	}

	@PutMapping("/author/unblockBook/{authorId}/{bookId}")
	public String unBlockBook(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId) throws BookUserMicroServiceException {
		Optional<User> user = userDao.findById(authorId);
		if (user.get().getRole().equals("ADMIN")) {
			Book newBook = bookClient.unBlockBookAUthor(authorId, bookId);
			if (newBook.getId() != 0) {
				return "Book got Unblocked ";
			} else {
				return "Book does not belong to this author";
			}
		} else {
			return "Only Authors can Unblock the book";
		}
	}
}
