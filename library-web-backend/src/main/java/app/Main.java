package app;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.net.URI;

import resource.BookResource;
import resource.LoanResource;
import resource.ReaderResource;

public class Main {
    public static void main(String[] args) {
        // Создание инжектора Guice и загрузка модуля
        Injector injector = Guice.createInjector(new LibraryModule());

        // Получение ресурсов через Guice
        BookResource bookResource = injector.getInstance(BookResource.class);
        ReaderResource readerResource = injector.getInstance(ReaderResource.class);
        LoanResource loanResource = injector.getInstance(LoanResource.class);

        // Создание конфигурации Jersey и регистрация ресурсов
        ResourceConfig config = new ResourceConfig();
        config.register(bookResource);
        config.register(readerResource);
        config.register(loanResource);

        // Запуск сервера Jetty
        URI baseUri = URI.create("http://localhost:8080/");
        try {
            JettyHttpContainerFactory.createServer(baseUri, config).start();
            System.out.println("Server started on: " + baseUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


