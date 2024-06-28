package service;

import dao.ReaderDAO;
import model.Reader;
import service.impl.ReaderService;
import com.google.inject.Inject;

public class ReaderServiceImpl implements ReaderService {
	
	private ReaderDAO readerDAO;

	@Inject
	public ReaderServiceImpl(ReaderDAO readerDAO) {
		this.readerDAO = readerDAO;
	}

	@Override
	public String getReaderById(Long id) {
		return readerDAO.findById(id);
	}

	@Override
	public String getAllReaders() {
		return readerDAO.findAll();
	}

	@Override
	public String addReader(Reader reader) {
		return readerDAO.save(reader);
	}

	@Override
	public String updateReader(Long id, Reader reader) {
		String existingReader = readerDAO.findById(id);
		if (existingReader != null) {
			reader.setId(id);
			return readerDAO.update(reader);
		} else {
			return "{\"status\":\"error\",\"message\":\"Reader not found\"}";
		}
	}

	@Override
	public String deleteReader(Long id) {
		return readerDAO.delete(id);
	}
}
