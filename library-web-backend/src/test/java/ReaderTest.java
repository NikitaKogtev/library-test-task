import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ReaderDAO;
import model.Reader;

public class ReaderTest {
	private EntityManager entityManager;
	private ReaderDAO readerDAO;

	@BeforeEach
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory("dbManager").createEntityManager();
		readerDAO = new ReaderDAO(entityManager);
		entityManager.getTransaction().begin();
	}

	@AfterEach
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	@Test
	public void testFindById() {
		Reader reader = new Reader();
		reader.setFullName("Мария Новикова");
		reader.setGender("Женский");
		reader.setAge(35);
		entityManager.persist(reader);

		String result = readerDAO.findById(reader.getId());

		assertNotNull(result);

		assertTrue(result.contains("\"id\":" + reader.getId()));
		assertTrue(result.contains("\"full_name\":\"Мария Новикова\""));
		assertTrue(result.contains("\"gender\":\"Женский\""));
		assertTrue(result.contains("\"age\":35"));
	}

	@Test
	public void testFindAll() {
		Reader reader1 = new Reader();
		reader1.setFullName("Петр Петров");
		reader1.setGender("Мужской");
		reader1.setAge(28);
		entityManager.persist(reader1);

		Reader reader2 = new Reader();
		reader2.setFullName("Елена Смирнова");
		reader2.setGender("Женский");
		reader2.setAge(31);
		entityManager.persist(reader2);

		String result = readerDAO.findAll();

		assertNotNull(result);

		assertTrue(result.contains("\"id\":" + reader1.getId()));
		assertTrue(result.contains("\"full_name\":\"Петр Петров\""));
		assertTrue(result.contains("\"gender\":\"Мужской\""));
		assertTrue(result.contains("\"age\":28"));

		assertTrue(result.contains("\"id\":" + reader2.getId()));
		assertTrue(result.contains("\"full_name\":\"Елена Смирнова\""));
		assertTrue(result.contains("\"gender\":\"Женский\""));
		assertTrue(result.contains("\"age\":31"));
	}

	@Test
	public void testSave() {
		Reader reader = new Reader();
		reader.setFullName("Иван Иванов");
		reader.setGender("Мужской");
		reader.setAge(30);

		String result = readerDAO.save(reader);

		assertEquals("{\"status\":\"success\"}", result);
		assertNotNull(reader.getId());
	}

	@Test
	public void testUpdate() {
		Reader reader = new Reader();
		reader.setFullName("Елена Петрова");
		reader.setGender("Женский");
		reader.setAge(25);
		entityManager.persist(reader);

		reader.setAge(26);

		String result = readerDAO.update(reader);

		assertEquals("{\"status\":\"success\"}", result);
	}

	@Test
	public void testDelete() {
		Reader reader = new Reader();
		reader.setFullName("Алексей Сидоров");
		reader.setGender("Мужской");
		reader.setAge(40);
		entityManager.persist(reader);

		String result = readerDAO.delete(reader.getId());

		assertEquals("{\"status\":\"success\"}", result);
		Reader deletedReader = entityManager.find(Reader.class, reader.getId());
		assertNull(deletedReader);
	}

}
