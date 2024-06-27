package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import com.google.inject.Inject;

import model.Book;
import service.BookService;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	private final BookService bookService;

	@Inject
	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	@GET
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GET
	@Path("/{id}")
	public Response getBookById(@PathParam("id") Long id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(book).build();
	}

	@POST
	public Response addBook(Book book) {
		bookService.addBook(book);
		return Response.status(Response.Status.CREATED).entity(book).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateBook(@PathParam("id") Long id, Book book) {
		Book existingBook = bookService.getBookById(id);
		if (existingBook == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		book.setId(id);
		bookService.updateBook(book);
		return Response.ok(book).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteBook(@PathParam("id") Long id) {
		Book existingBook = bookService.getBookById(id);
		if (existingBook == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		bookService.deleteBook(id);
		return Response.noContent().build();
	}
}
