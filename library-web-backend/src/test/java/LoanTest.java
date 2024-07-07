import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.LoanDAO;
import model.Book;
import model.Loan;
import model.Reader;

public class LoanTest {
	private EntityManager entityManager;
	private LoanDAO loanDAO;

	@BeforeEach
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory("dbManager").createEntityManager();
		loanDAO = new LoanDAO(entityManager);
		entityManager.getTransaction().begin();
	}

	@AfterEach
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	@Test
	public void testFindById() {
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2023);
		entityManager.persist(book);

		Reader reader = new Reader();
		reader.setFullName("Тестовый читатель");
		reader.setGender("Мужской");
		reader.setAge(30);
		entityManager.persist(reader);

		Loan loan = new Loan();
		loan.setBook(book);
		loan.setReader(reader);
		loan.setLoanDate(LocalDate.of(2023, 1, 1));
		loan.setReturnDate(LocalDate.of(2023, 12, 31));
		entityManager.persist(loan);

		Long loanId = loan.getId();

		String result = loanDAO.findById(loanId);

		assertNotNull(result);
		assertTrue(result.contains("\"id\":" + loan.getId()));
		assertTrue(result.contains("\"bookId\":" + loan.getBook().getId()));
		assertTrue(result.contains("\"readerId\":" + loan.getReader().getId()));
		assertTrue(result.contains("\"loanDate\":\"2023-01-01\""));
		assertTrue(result.contains("\"returnDate\":\"2023-12-31\""));
	}

	@Test
	public void testFindAll() {
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2023);
		entityManager.persist(book);

		Reader reader = new Reader();
		reader.setFullName("Тестовый читатель");
		reader.setGender("Мужской");
		reader.setAge(30);
		entityManager.persist(reader);

		Loan loan = new Loan();
		loan.setBook(book);
		loan.setReader(reader);
		loan.setLoanDate(LocalDate.of(2023, 1, 1));
		loan.setReturnDate(LocalDate.of(2023, 12, 31));
		entityManager.persist(loan);

		String result = loanDAO.findAll();

		assertNotNull(result);
		assertTrue(result.contains("\"id\":" + loan.getId()));
		assertTrue(result.contains("\"bookId\":" + loan.getBook().getId()));
		assertTrue(result.contains("\"readerId\":" + loan.getReader().getId()));
		assertTrue(result.contains("\"loanDate\":\"2023-01-01\""));
		assertTrue(result.contains("\"returnDate\":\"2023-12-31\""));
	}

	@Test
	public void testSave() {
		Loan newLoan = new Loan();
		newLoan.setBook(entityManager.find(Book.class, 1L));
		newLoan.setReader(entityManager.find(Reader.class, 1L));
		newLoan.setLoanDate(LocalDate.of(2024, 1, 1));
		newLoan.setReturnDate(LocalDate.of(2024, 12, 31));

		String result = loanDAO.save(newLoan);

		assertEquals("{\"status\":\"success\"}", result);
		assertNotNull(newLoan.getId());
	}

	@Test
	public void testUpdate() {
		Loan loan = new Loan();
		loan.setBook(entityManager.find(Book.class, 1L));
		loan.setReader(entityManager.find(Reader.class, 1L));
		loan.setLoanDate(LocalDate.of(2024, 1, 1));
		loan.setReturnDate(LocalDate.of(2024, 12, 31));
		entityManager.persist(loan);
		
		loan.setReturnDate(LocalDate.of(2023, 11, 30));

		String result = loanDAO.update(loan);

		assertNotNull(result);
		assertTrue(result.contains("\"status\":\"success\""));

		Loan updatedLoan = entityManager.find(Loan.class, loan.getId());
		assertEquals(LocalDate.of(2023, 11, 30), updatedLoan.getReturnDate());
	}

	@Test
	public void testDelete() {
		Loan newLoan = new Loan();
		newLoan.setBook(entityManager.find(Book.class, 1L));
		newLoan.setReader(entityManager.find(Reader.class, 1L));
		newLoan.setLoanDate(LocalDate.of(2024, 1, 1));
		newLoan.setReturnDate(LocalDate.of(2024, 12, 31));
		entityManager.persist(newLoan);
		
		String result = loanDAO.delete(newLoan.getId());

		assertNotNull(result);
		assertTrue(result.contains("\"status\":\"success\""));

		Loan deletedLoan = entityManager.find(Loan.class, newLoan.getId());
		assertNull(deletedLoan);
	}
}
