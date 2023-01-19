
package com.digitalbooks.UserDigitalbooks.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.UserDigitalbooks.clientPayload.BookClient;
import com.digitalbooks.UserDigitalbooks.dao.UserDao;
import com.digitalbooks.UserDigitalbooks.entity.Book;
import com.digitalbooks.UserDigitalbooks.security.service.SubscriptionService;
import com.digitalbooks.UserDigitalbooks.security.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "user_security_scheme")
@CrossOrigin()
public class ReaderController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookClient bookClient;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubscriptionService subscriptionService;

	@PostConstruct
	public void initRoleAndUser() {
		userService.initRoleAndUser();
	}

	@GetMapping("/getReadersBooks/{username}")

	public List<Book> userBookSubscription(@RequestParam(name = "user", required = false) String username,
			@RequestParam(name = "bookId", required = false) Long bookId,
			@RequestParam(name = "subscription", required = false) boolean subscriptionToggle) {
		System.out.println("Data" + username + bookId + subscriptionToggle);
		if (userDao.findById(username) != null) {
			// Book usersBook = new Book();
			return bookClient.getBookByIdByAUser(username);
		}

		return null;
	}

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		System.out.println("Finding movies from movieservice");

		return bookClient.getAllBooks();
	}

	@GetMapping("/subscribe/{bookId}/{userId}")
	public String subscribeUserToBook(@PathVariable Long bookId, @PathVariable String userId) {
		Book tobeRead = bookClient.subcribeBookById(bookId);
		if (!tobeRead.isActive()) {
			return "Book got blocked/InActive by author";
		} else {
			if (!tobeRead.isSubscription()) {
				String subscriptionsListvalue = subscriptionService.subscribeBook(userId, bookId);
				System.out.println(subscriptionsListvalue);
				if (subscriptionsListvalue.equalsIgnoreCase("Subscribed Successfully"))
					return "Subscribed to the book successfully";
				else
					return "Book is already Subscribed";
			} else {
				return "Already subscribed by other User";
			}
		}
	}

	@GetMapping("/unsubscribe/{bookId}/{userId}")
	public String unSubscribeUserToBook(@PathVariable Long bookId, @PathVariable String userId) {
		Book tobeRead = bookClient.getBookById(bookId);
		if (!tobeRead.isActive())
			
			return "Book got blocked by author";
		else {
			String subscriptionsListvalue = subscriptionService.unSubscribeBook(bookId, userId);
			if (subscriptionsListvalue.equalsIgnoreCase("Unsubscribed successfully"))

				return "UnSubscribed to the book successfully";
			else

				return "Book cannot be cancelled after 24 hours";
		}
	}

	@GetMapping("/subscriptionBookList/{userName}")
	public List<Book> retrieveSubscribedBookList(@PathVariable String userName) {
		List<Book> subscribedBooks = subscriptionService.retrieveSubscribedBooksForUser(userName);
		System.out.println(subscribedBooks);
		return subscribedBooks;
	}

	@GetMapping("/readersubscriptionBook/{userName}/{subscriptionId}")
	public ResponseEntity<Book> retrieveUserSubscribedBook(@PathVariable String userName,
			@PathVariable Long subscriptionId) {
		Book book = subscriptionService.retrieveUserSubscribedBook(userName, subscriptionId);

		return ResponseEntity.ok(book);
	}

	@GetMapping("/readersubscriptionBookContent/{userName}/{bookId}")
	public ResponseEntity<String> retrieveUserSubscribedBookContent(@PathVariable String userName,
			@PathVariable Long bookId) {
		String bookContent = subscriptionService.retrieveUserSubscribedBookContent(userName, bookId);

		return ResponseEntity.ok(bookContent);
	}

}
