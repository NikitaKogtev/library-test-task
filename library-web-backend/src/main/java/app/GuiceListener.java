package app;

import javax.servlet.annotation.WebListener;
import com.sun.jersey.guice.JerseyServletModule;

import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import controller.BookController;
import controller.LoanController;
import controller.ReaderController;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.spi.container.servlet.*;

@WebListener
public class GuiceListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
    	System.out.println("Hello!");
        Injector injector = Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                
                bind(BookController.class);
                bind(LoanController.class);
                bind(ReaderController.class);
                serve("/rest/*").with(GuiceContainer.class);
            }
        }, new JpaPersistModule("db_manager"));

        injector.getInstance(JpaInitializer.class);
        return injector;
    }
}


class JpaInitializer {
	@Inject
	public JpaInitializer (PersistService persistService) {
		persistService.start();
	}
}
