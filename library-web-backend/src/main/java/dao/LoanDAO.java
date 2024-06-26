package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Loan;

import java.util.List;

public class LoanDAO {
    private EntityManager entityManager;

    public LoanDAO() {
        entityManager = Persistence.createEntityManagerFactory("library-web-backend").createEntityManager();
    }

    // Найти запись о займе по ID
    public Loan findById(Long id) {
        return entityManager.find(Loan.class, id);
    }

    // Найти все записи о займах
    public List<Loan> findAll() {
        return entityManager.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    // Сохранить запись о займе
    public void save(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(loan);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Обновить запись о займе
    public void update(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(loan);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Удалить запись о займе
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Loan loan = entityManager.find(Loan.class, id);
            if (loan != null) {
                entityManager.remove(loan);
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
