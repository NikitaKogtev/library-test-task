package app;

import dao.BookDAO;
import dao.LoanDAO;
import dao.ReaderDAO;
import service.BookService;
import service.LoanService;
import service.ReaderService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    @Provides
    public EntityManagerFactory provideEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("library-web-backend");
    }

    @Provides
    public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
}

