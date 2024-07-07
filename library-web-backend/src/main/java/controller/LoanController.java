package controller;

import com.google.inject.Inject;
import model.Loan;
import model.Reader;
import model.Book;
import service.BookServiceImpl;
import service.LoanServiceImpl;
import service.ReaderServiceImpl;

import java.time.LocalDate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/loans")
public class LoanController {
	private static final Logger logger = LogManager.getLogger(LoanController.class);

	private final LoanServiceImpl loanServiceImpl;
	private final ReaderServiceImpl readerServiceImpl;
	private final BookServiceImpl bookServiceImpl;

	@Inject
	public LoanController(LoanServiceImpl loanServiceImpl, ReaderServiceImpl readerServiceImpl,
			BookServiceImpl bookServiceImpl) {
		logger.info("LoanController inject");
		this.loanServiceImpl = loanServiceImpl;
		this.readerServiceImpl = readerServiceImpl;
		this.bookServiceImpl = bookServiceImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllLoans() {
		logger.info("Get all loans");
		return loanServiceImpl.getAllLoans();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoanById(@PathParam("id") Long id) {
		logger.info("Get loan for id");
		return loanServiceImpl.getLoanById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLoan(JSONObject jsonObject) throws JSONException {
		logger.info("Add loan");
		Loan loan = new Loan();
		loan.setLoanDate(LocalDate.parse(jsonObject.getString("loanDate")));
		loan.setReturnDate(LocalDate.parse(jsonObject.getString("returnDate")));

		Long readerId = jsonObject.getLong("readerId");
		String readerJSON = readerServiceImpl.getReaderById(readerId);
		Reader reader = new Reader();
		JSONObject readerObj = new JSONObject(readerJSON);
		reader.setId(readerObj.getLong("id"));
		reader.setFullName(readerObj.getString("full_name"));
		reader.setGender(readerObj.getString("gender"));
		reader.setAge(Integer.parseInt(readerObj.getString("age")));
		loan.setReader(reader);

		Long bookId = jsonObject.getLong("bookId");
		String bookJSON = bookServiceImpl.getBookById(bookId);
		Book book = new Book();
		JSONObject bookObj = new JSONObject(bookJSON);
		book.setId(bookObj.getLong("id"));
		book.setTitle(bookObj.getString("title"));
		book.setAuthor(bookObj.getString("author"));
		book.setPublishYear(bookObj.getInt("year"));
		loan.setBook(book);

		return loanServiceImpl.addLoan(loan);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateLoan(@PathParam("id") Long id, JSONObject jsonObject) throws JSONException {
		logger.info("Update loan with id {}: {}", id, jsonObject);
		Loan loan = new Loan();
		loan.setId(id);
		loan.setLoanDate(LocalDate.parse(jsonObject.getString("loanDate")));
		loan.setReturnDate(LocalDate.parse(jsonObject.getString("returnDate")));

		Long readerId = jsonObject.getLong("readerId");
		String readerJSON = readerServiceImpl.getReaderById(readerId);
		Reader reader = new Reader();
		JSONObject readerObj = new JSONObject(readerJSON);
		reader.setId(readerObj.getLong("id"));
		reader.setFullName(readerObj.getString("full_name"));
		reader.setGender(readerObj.getString("gender"));
		reader.setAge(Integer.parseInt(readerObj.getString("age")));
		loan.setReader(reader);

		Long bookId = jsonObject.getLong("bookId");
		String bookJSON = bookServiceImpl.getBookById(bookId);
		Book book = new Book();
		JSONObject bookObj = new JSONObject(bookJSON);
		book.setId(bookObj.getLong("id"));
		book.setTitle(bookObj.getString("title"));
		book.setAuthor(bookObj.getString("author"));
		book.setPublishYear(bookObj.getInt("year"));
		loan.setBook(book);

		return loanServiceImpl.updateLoan(id, loan);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteLoan(@PathParam("id") Long id) {
		logger.info("Delete loan");
		return loanServiceImpl.deleteLoan(id);
	}
}

