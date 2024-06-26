package app;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import resource.BookResource;
import resource.LoanResource;
import resource.ReaderResource;

public class ApplicationBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(BookResource.class).in(Scopes.SINGLETON);
        bind(ReaderResource.class).in(Scopes.SINGLETON);
        bind(LoanResource.class).in(Scopes.SINGLETON);
    }
}