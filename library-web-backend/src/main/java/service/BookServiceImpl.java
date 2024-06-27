package service;

import dao.BookDAO;
import model.Book;
import service.impl.BookService;

import com.google.inject.Inject;

public class BookServiceImpl implements BookService{
    private BookDAO bookDAO;

    @Inject
    public BookServiceImpl(BookDAO bookDAO) {
    	this.bookDAO = bookDAO;
    }
    
    // Найти книгу по ID
    public Book getBookById(Long id) {
        return bookDAO.findById(id);
    }

    // Найти все книги
    public String getAllBooks() {
        return bookDAO.findAll();
    }

    // Добавить книгу
    public void addBook(Book book) {
        bookDAO.save(book);
    }

    // Обновить книгу
    public void updateBook(Long id, Book book) {
    	Book existingBook = bookDAO.findById(id);
		book.setId(id);
        bookDAO.update(existingBook);
    }

    // Удалить книгу
    public void deleteBook(Long id) {
        bookDAO.delete(id);
    }

    // Закрыть ресурсы
    public void close() {
        bookDAO.close();
    }

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}
}
