package controller;

import model.Book;
import service.BookServiceImpl;
import com.google.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
	public String addBook(JSONObject jsonObject) throws JSONException {
		logger.info("Add book");
		Book book = new Book();
		book.setTitle(jsonObject.getString("title"));
		book.setAuthor(jsonObject.getString("author"));
		book.setPublishYear(Integer.parseInt(jsonObject.getString("year")));
		return bookServiceImpl.addBook(book);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@PathParam("id") Long id, JSONObject jsonObject) throws JSONException {
		logger.info("Update book with id {}: {}", id, jsonObject);
		Book book = new Book();
		book.setId(id);
		book.setTitle(jsonObject.getString("title"));
		book.setAuthor(jsonObject.getString("author"));
		book.setPublishYear(jsonObject.getInt("year"));
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
