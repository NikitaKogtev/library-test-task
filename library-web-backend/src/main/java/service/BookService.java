package service;

import dao.BookDAO;
import model.Book;

import java.util.List;

import com.google.inject.Inject;

public class BookService {
    private BookDAO bookDAO;

    @Inject
    public BookService(BookDAO bookDAO) {
    	this.bookDAO = bookDAO;
    }
    
    // Найти книгу по ID
    public Book getBookById(Long id) {
        return bookDAO.findById(id);
    }

    // Найти все книги
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    // Добавить книгу
    public void addBook(Book book) {
        bookDAO.save(book);
    }

    // Обновить книгу
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    // Удалить книгу
    public void deleteBook(Long id) {
        bookDAO.delete(id);
    }

    // Закрыть ресурсы
    public void close() {
        bookDAO.close();
    }
}
