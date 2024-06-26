package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Reader;

import java.util.List;

public class ReaderDAO {
    private EntityManager entityManager;

    public ReaderDAO() {
        entityManager = Persistence.createEntityManagerFactory("library-web-backend").createEntityManager();
    }

    // Найти читателя по ID
    public Reader findById(Long id) {
        return entityManager.find(Reader.class, id);
    }

    // Найти всех читателей
    public List<Reader> findAll() {
        return entityManager.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
    }

    // Сохранить читателя
    public void save(Reader reader) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(reader);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Обновить читателя
    public void update(Reader reader) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(reader);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    // Удалить читателя
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Reader reader = entityManager.find(Reader.class, id);
            if (reader != null) {
                entityManager.remove(reader);
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
