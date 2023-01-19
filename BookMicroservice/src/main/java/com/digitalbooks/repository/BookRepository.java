package com.digitalbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalbooks.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByAuthor(String UserName);

	public Book findOneById(Long id);

}
