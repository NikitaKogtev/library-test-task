package dao;

import model.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanDAO {

    private EntityManager entityManager;

    public LoanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();
    }

    public void update(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(loan);
        transaction.commit();
    }

    public void delete(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(loan);
        transaction.commit();
    }

    public Loan findById(Long id) {
        return entityManager.find(Loan.class, id);
    }

    public List<Loan> findAll() {
        TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM Loan l", Loan.class);
        return query.getResultList();
    }
}
