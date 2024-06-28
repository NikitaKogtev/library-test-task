package service.impl;

import model.Reader;

public interface ReaderService {
    String getReaderById(Long id);
    String getAllReaders();
    String addReader(Reader reader);
    String updateReader(Long id, Reader reader);
    String deleteReader(Long id);
	
}

