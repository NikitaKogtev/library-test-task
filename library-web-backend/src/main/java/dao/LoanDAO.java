package dao;

import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import model.Loan;
import java.util.List;

public class LoanDAO {
	private static final Logger logger = LogManager.getLogger(LoanDAO.class);

	private EntityManager entityManager;

	@Inject
	public LoanDAO(EntityManager entityManager) {
		logger.info("BookDAO inject");
		this.entityManager = entityManager;
	}

	public String findById(Long id) {
        try {
            Loan loan = entityManager.find(Loan.class, id);
            if (loan != null) {
                JSONObject loanJSON = new JSONObject();
                loanJSON.put("id", loan.getId());
                loanJSON.put("loanDate", loan.getLoanDate().toString());
                loanJSON.put("returnDate", loan.getReturnDate() != null ? loan.getReturnDate().toString() : null); 
                
                JSONObject readerJSON = new JSONObject();
                readerJSON.put("id", loan.getReader().getId());
                readerJSON.put("fullName", loan.getReader().getFullName());
                readerJSON.put("gender", loan.getReader().getGender());
                readerJSON.put("age", loan.getReader().getAge());
                loanJSON.put("reader", readerJSON);

                JSONObject bookJSON = new JSONObject();
                bookJSON.put("id", loan.getBook().getId());
                bookJSON.put("title", loan.getBook().getTitle());
                bookJSON.put("author", loan.getBook().getAuthor());
                bookJSON.put("publishYear", loan.getBook().getPublishYear());
                loanJSON.put("book", bookJSON);

                logger.info("LoanDAO find by id {}: {}", id, loanJSON);
                return loanJSON.toString();
            } else {
                logger.warn("Loan with id {} not found", id);
                return null;
            }
        } catch (Exception ex) {
            logger.error("LoanDAO error find by id {}: {}", id, ex.getMessage());
            return null;
        }
    }

	public String findAll() {
		List<Loan> loans = entityManager.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		try {
			for (Loan loan : loans) {
				JSONObject loanJSON = new JSONObject();
				loanJSON.put("id", loan.getId());
				loanJSON.put("loanDate", loan.getLoanDate().toString());
				loanJSON.put("returnDate", loan.getReturnDate() != null ? loan.getReturnDate().toString() : null);

				JSONObject readerJSON = new JSONObject();
				readerJSON.put("id", loan.getReader().getId());
				readerJSON.put("fullName", loan.getReader().getFullName());
				readerJSON.put("gender", loan.getReader().getGender());
				readerJSON.put("age", loan.getReader().getAge());
				loanJSON.put("reader", readerJSON);

				JSONObject bookJSON = new JSONObject();
				bookJSON.put("id", loan.getBook().getId());
				bookJSON.put("title", loan.getBook().getTitle());
				bookJSON.put("author", loan.getBook().getAuthor());
				bookJSON.put("publishYear", loan.getBook().getPublishYear());
				loanJSON.put("book", bookJSON);

				jsonArray.put(loanJSON);
				logger.info("LoanDAO get all book: {}", jsonArray);
			}
		} catch (Exception ex) {
			logger.error("LoanDAO error find all book: {}", ex.getMessage());
		}
		return jsonArray.toString();
	}

	@Transactional
	public String save(Loan loan) {
		JSONObject response = new JSONObject();
		try {
			entityManager.persist(loan);
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("LoanDAO error save loan: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("LoanDAO save: {}", response.toString());
		return response.toString();
	}

	public String update(Loan loan) {
		JSONObject response = new JSONObject();
		try {
			entityManager.merge(loan);
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("LoanDAO error update loan: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("LoanDAO update: {}", response.toString());
		return response.toString();
	}

	@Transactional
	public String delete(Long id) {
		JSONObject response = new JSONObject();
		try {
			Loan loan = entityManager.find(Loan.class, id);
			if (loan != null) {
				entityManager.remove(loan);
			}
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("LoanDAO error delete loan: {}", e.getMessage());
			try {
				response.put("status", "error");
				response.put("message", e.getMessage());
			} catch (Exception jsonException) {
				jsonException.printStackTrace();
			}
		}
		logger.info("LoanDAO delete: {}", response.toString());
		return response.toString();
	}
}
