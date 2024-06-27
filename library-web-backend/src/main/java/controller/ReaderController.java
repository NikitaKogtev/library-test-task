package controller;


import com.google.inject.Inject;

import model.Reader;
import service.ReaderServiceImpl;

import java.util.List;

import javax.ws.rs.*;

@Path("/readers")
public class ReaderController {
	private final ReaderServiceImpl readerService;

    @Inject
    public ReaderController(ReaderServiceImpl readerService) {
        this.readerService = readerService;
    }

    @GET
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GET
    @Path("/{id}")
    public Reader getReaderById(@PathParam("id") Long id) {
        return readerService.getReaderById(id);
      
    }

    @POST
    public void addReader(Reader reader) {
        readerService.addReader(reader);
    }

    @PUT
    @Path("/{id}")
    public void updateReader(@PathParam("id") Long id, Reader reader) {
        Reader existingReader = readerService.getReaderById(id);
        reader.setId(id);
        readerService.updateReader(reader);
    }

    @DELETE
    @Path("/{id}")
    public void deleteReader(@PathParam("id") Long id) {
        Reader existingReader = readerService.getReaderById(id);
        readerService.deleteReader(id);
    }
}
