package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Singleton;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

@Singleton
public class JerseyConfig extends JerseyServletModule {
	private static final Logger logger = LogManager.getLogger(JerseyConfig.class);

	@Override
	protected void configureServlets() {
		logger.info("Serve all requests through Guice container");
		serve("/rest/*").with(GuiceContainer.class);
	}
}
