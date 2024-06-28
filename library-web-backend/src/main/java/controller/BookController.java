package controller;

import model.Book;
import service.BookServiceImpl;
import com.google.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/books")
public class BookController {
	private static final Logger logger = LogManager.getLogger(BookController.class);

	private final BookServiceImpl bookServiceImpl;

	@Inject
	public BookController(BookServiceImpl bookServiceImpl) {
		logger.info("BookController inject");
		this.bookServiceImpl = bookServiceImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllBooks() {
		logger.info("Get all books");
		return bookServiceImpl.getAllBooks();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookById(@PathParam("id") Long id) {
		logger.info("Get books for id");
		return bookServiceImpl.getBookById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addBook(Book book) {
		logger.info("Add book");
		return bookServiceImpl.addBook(book);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@PathParam("id") Long id, Book book) {
		logger.info("Update book");
		return bookServiceImpl.updateBook(id, book);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBook(@PathParam("id") Long id) {
		logger.info("Delete book");
		return bookServiceImpl.deleteBook(id);
	}
}
