package com.digitalbooks.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String category;
	private String logoImage;
	private double price;
	private String author;
	private String author_id;
	private String publisher;
	private Date publishedDate;
	private boolean active;
	private String content;
	private boolean subscription;

	public long getId() {
		return id;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", category=" + category + ", logoImage=" + logoImage
				+ ", price=" + price + ", author=" + author + ", author_id=" + author_id + ", publisher=" + publisher
				+ ", publishedDate=" + publishedDate + ", active=" + active + ", content=" + content + ", subscription="
				+ subscription + "]";
	}

	public Book(long id, String title, String category, String logoImage, double price, String author, String author_id,
			String publisher, Date publishedDate, boolean active, String content, boolean subscription) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.logoImage = logoImage;
		this.price = price;
		this.author = author;
		this.author_id = author_id;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.active = active;
		this.content = content;
		this.subscription = subscription;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
}
