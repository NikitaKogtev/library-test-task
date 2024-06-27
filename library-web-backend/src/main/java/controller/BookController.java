package controller;

import model.Book;
import service.BookServiceImpl;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@Path("/books")
public class BookController {
	private final BookServiceImpl bookServiceImpl;

	@Inject
	public BookController(BookServiceImpl bookService) {
		System.out.println("HI! INJECT BOOK");
		this.bookServiceImpl = bookService;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
    public String getAllBooks() {
		System.out.println("HI!");
		return bookServiceImpl.getAllBooks();
			
    }

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Book getBookById(@PathParam("id") Long id) {
		return bookServiceImpl.getBookById(id);
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public void addBook(Book book) {
		bookServiceImpl.addBook(book);

	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void updateBook(@PathParam("id") Long id, Book book) {
		bookServiceImpl.updateBook(id, book);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteBook(@PathParam("id") Long id) {
		bookServiceImpl.deleteBook(id);
	}
}
