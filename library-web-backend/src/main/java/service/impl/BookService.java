package service.impl;

import model.Book;

public interface BookService {
	String getBookById(Long id);
	String getAllBooks();
	String addBook(Book book);
	String updateBook(Long id, Book book);
	String deleteBook(Long id);
}
