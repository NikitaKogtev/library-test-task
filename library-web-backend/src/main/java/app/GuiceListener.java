package app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import controller.BookController;
import controller.LoanController;
import controller.ReaderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebListener;

@WebListener
public class GuiceListener extends GuiceServletContextListener {
	private static final Logger logger = LogManager.getLogger(GuiceListener.class);

	@Override
	protected Injector getInjector() {
		logger.info("Initializing Guice Injector");
		
		Injector injector = Guice.createInjector(new AppModule(), new JerseyConfig());
		
		injector.getInstance(JpaInitializer.class);

		injector.getInstance(BookController.class);
		injector.getInstance(ReaderController.class);
		injector.getInstance(LoanController.class);
		
		logger.info("Guice Injector initialized successfully. Injector - {}", injector);

		return injector;
	}
}