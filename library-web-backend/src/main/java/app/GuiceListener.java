package app;

import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger logger = LogManager.getLogger(GuiceListener.class);

	@Override
	protected Injector getInjector() {
		logger.info("Run Injector");
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
		System.out.println("End!");
		return injector;
	}
}

class JpaInitializer {
	private static final Logger logger = LogManager.getLogger(JpaInitializer.class);

	@Inject
	public JpaInitializer(PersistService persistService) {
		logger.info("Run Persost Service");
		persistService.start();
	}
}
