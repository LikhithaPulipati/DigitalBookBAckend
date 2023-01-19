package com.digitalbooks;

import static org.hamcrest.CoreMatchers.any;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digitalbooks.model.Book;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.service.BookService;

@SpringBootTest
class BookServiceTest {

	@MockBean
	//@Autowired
	BookRepository bookRepository;

	@Autowired
	private BookService bookService;
	
	Date newdate = new Date(2023, 01, 04);

//	@Test
//	public void shouldCreateBook()  {
//		//System.out.println(service);
//		Book book1 =new Book();
//		Book book2 =new Book(23, "Swings", "knowledge", "Swings", 85000, "likhitha", "likhitha", "prasad", newdate, true, "Java swings", false);
//		book1.setTitle("Swings");
//		book1.setId(23);
//		Mockito	.when(bookService.save(book1))
//			.thenReturn(book2);
//		 bookService.save(book1);
//		 boolean isPlaced = false ;
//		 if(book1.getTitle().equals( book2.getTitle()))
//			 isPlaced = true;
//		
//		Assertions.assertTrue(isPlaced);
//	}

//	@Test
//	void contextLoads() {
//	}
//BookMicroserviceApplicationTests
}
