package app;

import javax.servlet.annotation.WebListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import controller.BookController;
import controller.LoanController;
import controller.ReaderController;
import service.*;
import dao.*;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

@WebListener
public class GuiceListener extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		System.out.print("HELLO!"); 
		Injector injector = Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				bind(BookController.class);
				bind(LoanController.class);
				bind(ReaderController.class);
				bind(BookServiceImpl.class);
				bind(LoanServiceImpl.class);
				bind(ReaderServiceImpl.class);
				bind(BookDAO.class);
				bind(LoanDAO.class);
				bind(ReaderDAO.class);
			}
		}, new JpaPersistModule("db_manager"));
		injector.getInstance(JpaInitializer.class);
		System.out.print("E!");
		return injector;
	}
}

class JpaInitializer {
	@Inject
	public JpaInitializer (PersistService persistService) {
		persistService.start();
	}
}
