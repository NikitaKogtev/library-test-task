package dao;

import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import model.Book;
import java.util.List;

public class BookDAO {
	private static final Logger logger = LogManager.getLogger(BookDAO.class);

	private EntityManager entityManager;

	@Inject
	public BookDAO(EntityManager entityManager) {
		logger.info("BookDAO inject");
		this.entityManager = entityManager;
	}

	public String findById(Long id) {
		Book book = entityManager.find(Book.class, id);
		JSONObject bookJSON = new JSONObject();
		try {
			if (book != null) {
				bookJSON.put("id", book.getId());
				bookJSON.put("title", book.getTitle());
				bookJSON.put("author", book.getAuthor());
				bookJSON.put("year", book.getPublishYear());
			}
		} catch (Exception ex) {
			logger.error("BookDAO error find for id: {}", ex.getMessage());
		}
		logger.info("BookDAO get for id: {}", bookJSON);
		return bookJSON.toString();
	}

	public String findAll() {
		List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		try {
			for (Book book : books) {
				JSONObject bookJSON = new JSONObject();
				bookJSON.put("id", book.getId());
				bookJSON.put("title", book.getTitle());
				bookJSON.put("author", book.getAuthor());
				bookJSON.put("year", book.getPublishYear());
				jsonArray.put(bookJSON);
			}
		} catch (Exception ex) {
			logger.error("BookDAO error find all book: {}", ex.getMessage());
		}
		logger.info("BookDAO get all book: {}", jsonArray);
		return jsonArray.toString();
	}

	@Transactional
	public String save(Book book) {
		JSONObject response = new JSONObject();
		try {
			entityManager.persist(book);
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("BookDAO error save book: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("BookDAO save: {}", response.toString());
		return response.toString();
	}

	@Transactional
	public String update(Book book) {
		JSONObject response = new JSONObject();
		try {
			entityManager.merge(book);
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("BookDAO error update book: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("BookDAO update: {}", response.toString());
		return response.toString();
	}

	@Transactional
	public String delete(Long id) {
		JSONObject response = new JSONObject();
		try {
			Book book = entityManager.find(Book.class, id);
			if (book != null) {
				entityManager.remove(book);
			}
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("BookDAO error delete book: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("BookDAO delete: {}", response.toString());
		return response.toString();
	}
}
