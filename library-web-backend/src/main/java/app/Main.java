package app;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.servlet.ServletHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        final ResourceConfig resourceConfig = new ResourceConfig()
                .packages("com.example.resources")
                .register(new ApplicationBinder());

        HttpServer server = HttpServer.createSimpleServer();
        ServerConfiguration config = server.getServerConfiguration();
        
        // Setup ServletHandler with Jersey and Guice integration
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServlet("jerseyServlet", new ServletContainer(resourceConfig))
                .addMapping("/*");

        config.addHttpHandler(servletHandler);

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

        server.start();
        System.out.println("Application started at http://localhost:8080");
        Thread.currentThread().join();
    }
}

