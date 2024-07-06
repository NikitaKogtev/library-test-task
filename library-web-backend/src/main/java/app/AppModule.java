package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import controller.BookController;
import controller.LoanController;
import controller.ReaderController;

public class AppModule extends AbstractModule {
	private static final Logger logger = LogManager.getLogger(AppModule.class);

	@Override
	protected void configure() {
		install(new JpaPersistModule("dbManager"));

		bind(BookController.class);
		bind(LoanController.class);
		bind(ReaderController.class);

		logger.info("Instal JPA Module and Bind Controllers");
	}
}
