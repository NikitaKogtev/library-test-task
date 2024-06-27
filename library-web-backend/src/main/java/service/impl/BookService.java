package service.impl;

import model.Book;

public interface BookService {
	Book getBookById(Long id);
	String getAllBooks();
	void addBook(Book book);
	void updateBook(Book book);
	void deleteBook(Long id);
}
