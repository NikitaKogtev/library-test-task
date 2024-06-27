package service;

import dao.ReaderDAO;
import model.Reader;
import service.impl.ReaderService;

import java.util.List;

import com.google.inject.Inject;

public class ReaderServiceImpl implements ReaderService {
    private ReaderDAO readerDAO;

    @Inject
    public ReaderServiceImpl(ReaderDAO readerDAO) {
    	this.readerDAO = readerDAO;
    }
    
    // Найти читателя по ID
    public Reader getReaderById(Long id) {
        return readerDAO.findById(id);
    }

    // Найти всех читателей
    public List<Reader> getAllReaders() {
        return readerDAO.findAll();
    }

    // Добавить читателя
    public void addReader(Reader reader) {
        readerDAO.save(reader);
    }

    // Обновить читателя
    public void updateReader(Reader reader) {
        readerDAO.update(reader);
    }

    // Удалить читателя
    public void deleteReader(Long id) {
        readerDAO.delete(id);
    }

    // Закрыть ресурсы
    public void close() {
        readerDAO.close();
    }
}

