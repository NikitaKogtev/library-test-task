package service.impl;

import model.Reader;
import java.util.List;

public interface ReaderService {
    Reader getReaderById(Long id);
    List<Reader> getAllReaders();
    void addReader(Reader reader);
    void updateReader(Reader reader);
    void deleteReader(Long id);
}

