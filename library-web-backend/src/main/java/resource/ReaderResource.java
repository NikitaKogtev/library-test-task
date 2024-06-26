package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import model.Reader;
import service.ReaderService;

import java.util.List;

@Path("/readers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReaderResource {
	private final ReaderService readerService;

    @Inject
    public ReaderResource(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GET
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GET
    @Path("/{id}")
    public Response getReaderById(@PathParam("id") Long id) {
        Reader reader = readerService.getReaderById(id);
        if (reader == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(reader).build();
    }

    @POST
    public Response addReader(Reader reader) {
        readerService.addReader(reader);
        return Response.status(Response.Status.CREATED).entity(reader).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateReader(@PathParam("id") Long id, Reader reader) {
        Reader existingReader = readerService.getReaderById(id);
        if (existingReader == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        reader.setId(id);
        readerService.updateReader(reader);
        return Response.ok(reader).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReader(@PathParam("id") Long id) {
        Reader existingReader = readerService.getReaderById(id);
        if (existingReader == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        readerService.deleteReader(id);
        return Response.noContent().build();
    }
}
