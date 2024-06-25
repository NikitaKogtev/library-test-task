package dao;

import model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

public class BookDAO {

	private EntityManager entityManager;

	public BookDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(book);
		transaction.commit();
	}

	public void update(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(book);
		transaction.commit();
	}

	public void delete(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(book);
		transaction.commit();
	}

	public Book findById(Long id) {
		return entityManager.find(Book.class, id);
	}

	public List<Book> findAll() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}
}
