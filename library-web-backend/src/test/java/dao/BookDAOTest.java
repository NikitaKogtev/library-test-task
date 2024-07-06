package dao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Book;

public class BookDAOTest {

		private EntityManager entityManager;
		private BookDAO bookDAO;
	
		@BeforeEach
		public void setUp() {
			entityManager = Persistence.createEntityManagerFactory("dbManager").createEntityManager();
			bookDAO = new BookDAO(entityManager);
			entityManager.getTransaction().begin();
		}
	
		@AfterEach
		public void tearDown() {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}

	@Test
	public void testFindById() {
		// Подготовка данных для теста
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2023);

		entityManager.persist(book);
		Long bookId = book.getId();

		// Вызов метода поиска по ID
		String result = bookDAO.findById(bookId);
		assertNotNull(result);
		// Проверка, что найденная книга соответствует ожиданиям
		assertTrue(result.contains("\"id\":" + bookId));
		assertTrue(result.contains("\"title\":\"Тестовая книга\""));
		assertTrue(result.contains("\"author\":\"Тестовый автор\""));
		assertTrue(result.contains("\"year\":2023"));
	}

	@Test
	public void testFindAll() {
		Book book1 = new Book();
		book1.setTitle("Книга 1");
		book1.setAuthor("Автор 1");
		book1.setPublishYear(2000);
		entityManager.persist(book1);

		Book book2 = new Book();
		book2.setTitle("Книга 2");
		book2.setAuthor("Автор 2");
		book2.setPublishYear(2010);
		entityManager.persist(book2);

		String result = bookDAO.findAll();
		
		assertNotNull(result);
		
		assertTrue(result.contains("\"id\":" + book1.getId()));
		assertTrue(result.contains("\"title\":\"Книга 1\""));
		assertTrue(result.contains("\"author\":\"Автор 1\""));
		assertTrue(result.contains("\"year\":2000"));

		assertTrue(result.contains("\"id\":" + book2.getId()));
		assertTrue(result.contains("\"title\":\"Книга 2\""));
		assertTrue(result.contains("\"author\":\"Автор 2\""));
		assertTrue(result.contains("\"year\":2010"));
	}

	@Test
	public void testSave() {
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2022);

		String result = bookDAO.save(book);

		assertEquals("{\"status\":\"success\"}", result);
		assertNotNull(book.getId());
	}

	@Test
	public void testUpdate() {
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2022);
		entityManager.persist(book);

		// Изменяем данные книги
		book.setTitle("Новое название");

		String result = bookDAO.update(book);

		assertEquals("{\"status\":\"success\"}", result);

		Book updatedBook = entityManager.find(Book.class, book.getId());
		assertEquals("Новое название", updatedBook.getTitle());
	}

	@Test
	public void testDelete() {
		Book book = new Book();
		book.setTitle("Тестовая книга");
		book.setAuthor("Тестовый автор");
		book.setPublishYear(2022);
		entityManager.persist(book);

		String result = bookDAO.delete(book.getId());

		assertEquals("{\"status\":\"success\"}", result);
		Book deletedBook = entityManager.find(Book.class, book.getId());
		assertNull(deletedBook);
	}
	
}