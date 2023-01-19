package com.digitalbooks.UserDigitalbooks.security.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.UserDigitalbooks.clientPayload.BookClient;
import com.digitalbooks.UserDigitalbooks.dao.SubscriptionDao;
import com.digitalbooks.UserDigitalbooks.dao.UserDao;
import com.digitalbooks.UserDigitalbooks.entity.Book;
import com.digitalbooks.UserDigitalbooks.entity.Subscription;
import com.digitalbooks.UserDigitalbooks.entity.User;

@Service
public class SubscriptionService {

	@Autowired
	UserDao userDao;

	@Autowired
	SubscriptionDao subscriptionDao;

	@Autowired
	BookClient bookClient;

	public String subscribeBook(String userId, Long bookId) {
		Optional<User> user = userDao.findById(userId);
		List<Subscription> subscriptions = subscriptionDao.findAllByUserName(userId);
		List<Long> bookIds = subscriptions.stream().map(s -> s.getBookId()).collect(Collectors.toList());
		if (!bookIds.contains(bookId)) {
			if (user != null) {
				Subscription newSubscription = new Subscription();
				newSubscription.setBookId(bookId);
				newSubscription.setSubscribedDate(LocalDate.now());
				newSubscription.setUserName(userId);

				subscriptionDao.save(newSubscription);

				return "Subscribed Successfully";
			} else
				return "Not a Valid User";
		}
		return "Already subscription exits";

	}

	public String unSubscribeBook(Long bookId, String userId) {
		List<Subscription> subscriptionListByName = subscriptionDao.findAllByUserName(userId);
		if (subscriptionListByName != null) {
			for (Subscription sub : subscriptionListByName) {
				if (sub.getBookId().equals(bookId)) {
					LocalDate subscriptionnextDay = sub.getSubscribedDate().plusDays(1);
					if (!LocalDate.now().isAfter(subscriptionnextDay)) {
						subscriptionDao.delete(sub);
						return "Unsubscribed successfully";
					} else {
						return "Book cannot be cancelled after 24 hours";
					}
				}
			}
		} else {
			return "No Books are Subscribed";
		}

		return "Not valid request";

	}

	public List<Book> retrieveSubscribedBooksForUser(String userId) {
		List<Book> allSubscribedBooks = new ArrayList<>();
		List<Subscription> subscriptions = subscriptionDao.findAllByUserName(userId);
		List<Long> bookIds = subscriptions.stream().map(s -> s.getBookId()).collect(Collectors.toList());
		for (Long bookId : bookIds)
			allSubscribedBooks.add(bookClient.getBookById(bookId));
		return allSubscribedBooks;
	}

	public Book retrieveUserSubscribedBook(String userId, Long subscriptionId) {
		Book subscribedBook = new Book();
		Optional<Subscription> subscription = subscriptionDao.findById(subscriptionId);
		Long bookId = subscription.get().getBookId();
		subscribedBook = bookClient.getBookById(bookId);

		return subscribedBook;
	}

	public String retrieveUserSubscribedBookContent(String userId, Long bookId) {
		Book subscribedBook = new Book();
		subscribedBook = bookClient.getBookById(bookId);

		return subscribedBook.getContent();
	}

}
