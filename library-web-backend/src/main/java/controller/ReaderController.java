package controller;

import com.google.inject.Inject;

import model.Reader;
import service.ReaderServiceImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/readers")
public class ReaderController {
	private static final Logger logger = LogManager.getLogger(ReaderController.class);

	private final ReaderServiceImpl readerServiceImpl;

	@Inject
	public ReaderController(ReaderServiceImpl readerServiceImpl) {
		logger.info("ReaderController inject");
		this.readerServiceImpl = readerServiceImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllReaders() {
		logger.info("Get all readers");
		return readerServiceImpl.getAllReaders();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getReaderById(@PathParam("id") Long id) {
		logger.info("Get reader for id");
		return readerServiceImpl.getReaderById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addReader(Reader reader) {
		logger.info("Add reader");
		return readerServiceImpl.addReader(reader);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateReader(@PathParam("id") Long id, Reader reader) {
		logger.info("Update reader");
		return readerServiceImpl.updateReader(id, reader);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteReader(@PathParam("id") Long id) {
		logger.info("Delete reader");
		return readerServiceImpl.deleteReader(id);
	}
}
