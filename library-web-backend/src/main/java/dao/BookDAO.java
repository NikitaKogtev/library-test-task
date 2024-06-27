package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.codehaus.jettison.json.JSONArray;

import org.codehaus.jettison.json.JSONObject;
import com.google.inject.Inject;

import model.Book;

import java.util.List;

public class BookDAO {
	private EntityManager entityManager;

	@Inject
	public BookDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Найти книгу по ID
	public Book findById(Long id) {
		return entityManager.find(Book.class, id);
	}

	// Найти все книги
	public String findAll() {
    	List<Book> books = entityManager.createQuery("SELECT * FROM Book b", Book.class).getResultList();
    	JSONArray jsonArray = new JSONArray();
    	JSONObject jsonObject = new JSONObject();
    	try { 
    	for (Book book : books) {
        	 JSONObject bookJSON = new JSONObject();
        	 bookJSON.put("id", book.getId());
        	 bookJSON.put("title", book.getTitle());
        	 bookJSON.put("author", book.getAuthor());
        	 bookJSON.put("year", book.getPublishYear());
             jsonArray.put(bookJSON);
             jsonObject.put("books", jsonArray);
        }
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return jsonObject.toString();
    }

	// Сохранить книгу
	public void save(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(book);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	// Обновить книгу
	public void update(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(book);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	// Удалить книгу
	public void delete(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Book book = entityManager.find(Book.class, id);
			if (book != null) {
				entityManager.remove(book);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	// Закрыть EntityManager
	public void close() {
		entityManager.close();
	}
}
