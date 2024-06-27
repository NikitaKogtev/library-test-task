package app;

import dao.BookDAO;
import dao.LoanDAO;
import dao.ReaderDAO;
import service.BookService;
import service.LoanService;
import service.ReaderService;

import com.google.inject.AbstractModule;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        // Привязка сервисов и DAO
        bind(BookService.class);
        bind(ReaderService.class);
        bind(LoanService.class);
        bind(BookDAO.class);
        bind(ReaderDAO.class);
        bind(LoanDAO.class);
    }
}

