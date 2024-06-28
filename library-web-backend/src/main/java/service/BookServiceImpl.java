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
    
    @Override
    public String getBookById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public String getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public String addBook(Book book) {
        return bookDAO.save(book);
    }

    @Override
    public String updateBook(Long id, Book book) {
        String existingBook = bookDAO.findById(id);
        if (existingBook != null) {
            book.setId(id);
            return bookDAO.update(book);
        } else {
            return "{\"status\":\"error\",\"message\":\"Book not found\"}";
        }
    }

    @Override
    public String deleteBook(Long id) {
        return bookDAO.delete(id);
    }
}
