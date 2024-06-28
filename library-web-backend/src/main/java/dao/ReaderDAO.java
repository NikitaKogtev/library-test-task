package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import model.Reader;
import java.util.List;

public class ReaderDAO {
	private static final Logger logger = LogManager.getLogger(ReaderDAO.class);

	private EntityManager entityManager;

	@Inject
	public ReaderDAO(EntityManager entityManager) {
		logger.info("ReaderDAO inject");
		this.entityManager = entityManager;
	}

	public String findById(Long id) {
		Reader reader = entityManager.find(Reader.class, id);
		JSONObject readerJSON = new JSONObject();
		try {
			if (reader != null) {
				readerJSON.put("id", reader.getId());
				readerJSON.put("full_name", reader.getFullName());
				readerJSON.put("gender", reader.getGender());
				readerJSON.put("age", reader.getAge());
			}
		} catch (Exception ex) {
			logger.error("ReaderDAO error find for id: {}", ex.getMessage());
		}
		logger.info("ReaderDAO get for id: {}", readerJSON);
		return readerJSON.toString();
	}

	public String findAll() {
		List<Reader> readers = entityManager.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		try {
			for (Reader reader : readers) {
				JSONObject readerJSON = new JSONObject();
				readerJSON.put("id", reader.getId());
				readerJSON.put("full_name", reader.getFullName());
				readerJSON.put("gender", reader.getGender());
				readerJSON.put("age", reader.getAge());
				jsonArray.put(readerJSON);
			}
		} catch (Exception ex) {
			logger.error("ReaderDAO error find all reader: {}", ex.getMessage());
		}
		logger.info("ReaderDAO get all reader: {}", jsonArray);
		return jsonArray.toString();
	}

	@Transactional
	public String save(Reader reader) {
		EntityTransaction transaction = entityManager.getTransaction();
		JSONObject response = new JSONObject();
		try {
			transaction.begin();
			entityManager.persist(reader);
			transaction.commit();
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("ReaderDAO error save reader: {}", e.getMessage());
			transaction.rollback();
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("ReaderDAO save: {}", response.toString());
		return response.toString();
	}

	@Transactional
	public String update(Reader reader) {
		EntityTransaction transaction = entityManager.getTransaction();
		JSONObject response = new JSONObject();
		try {
			transaction.begin();
			entityManager.merge(reader);
			transaction.commit();
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("ReaderDAO error update reader: {}", e.getMessage());
			transaction.rollback();
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("ReaderDAO update: {}", response.toString());
		return response.toString();
	}

	@Transactional
	public String delete(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		JSONObject response = new JSONObject();
		try {
			transaction.begin();
			Reader reader = entityManager.find(Reader.class, id);
			if (reader != null) {
				entityManager.remove(reader);
			}
			transaction.commit();
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("ReaderDAO error delete reader: {}", e.getMessage());
			transaction.rollback();
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("ReaderDAO delete: {}", response.toString());
		return response.toString();
	}
}
