package service;

import dao.BookDAO;
import model.Book;

import javax.persistence.EntityManager;

import java.util.List;

public class BookService {

	private EntityManager entityManager;

	public BookService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void addBook(Book book) {
		BookDAO bookDAO = new BookDAO(entityManager);
		bookDAO.save(book);
	}

	public void updateBook(Book book) {
		BookDAO bookDAO = new BookDAO(entityManager);
		bookDAO.update(book);
	}

	public void deleteBook(Book book) {
		BookDAO bookDAO = new BookDAO(entityManager);
		bookDAO.delete(book);
	}

	public Book getBookById(Long id) {
		BookDAO bookDAO = new BookDAO(entityManager);
		return bookDAO.findById(id);
	}

	public List<Book> getAllBooks() {
		BookDAO bookDAO = new BookDAO(entityManager);
		return bookDAO.findAll();
	}
}
