package com.digitalbooks.UserDigitalbooks.clientPayload;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digitalbooks.UserDigitalbooks.entity.Book;

@FeignClient(value = "books", url = "http://localhost:8081")
public interface BookClient {

	@GetMapping("/api/v1/digitalbooks/readbooks")
	public List<Book> getAllBooks();

	@GetMapping("/api/v1/digitalbooks/readbooks/{id}")
	public Book getBookById(@PathVariable Long id);

	@GetMapping("/api/v1/digitalbooks/subscribeToBookById/{id}")
	public Book subcribeBookById(@PathVariable Long id);

	@GetMapping("/api/v1/digitalbooks/readbooksByUser/{username}")
	public List<Book> getBookByIdByAUser(@PathVariable String username);

	@PostMapping("/api/v1/digitalbooks/createbook")
	public Book createBook(@RequestBody Book book);

	@PutMapping("/api/v1/digitalbooks/updatebook/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book);

	@PutMapping("/api/v1/digitalbooks/blockBook/{authorId}/{bookId}")
	public Book blockBookAUthor(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId);

	@PutMapping("/api/v1/digitalbooks/unblockBook/{authorId}/{bookId}")
	public Book unBlockBookAUthor(@PathVariable(name = "authorId") String authorId,
			@PathVariable(name = "bookId") Long bookId);

}
