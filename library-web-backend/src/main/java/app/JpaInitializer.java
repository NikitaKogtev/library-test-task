package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInitializer {
	private static final Logger logger = LogManager.getLogger(JpaInitializer.class);

	@Inject
	public JpaInitializer(PersistService persistService) {
		logger.info("JpaInitializer inject");
		persistService.start();
	}
}
