package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import model.Loan;
import service.LoanService;

import java.util.List;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {
	private final LoanService loanService;

	@Inject
	public LoanResource(LoanService loanService) {
		this.loanService = loanService;
	}

	@GET
	public List<Loan> getAllLoans() {
		return loanService.getAllLoans();
	}

	@GET
	@Path("/{id}")
	public Response getLoanById(@PathParam("id") Long id) {
		Loan loan = loanService.getLoanById(id);
		if (loan == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(loan).build();
	}

	@POST
	public Response addLoan(Loan loan) {
		loanService.addLoan(loan);
		return Response.status(Response.Status.CREATED).entity(loan).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateLoan(@PathParam("id") Long id, Loan loan) {
		Loan existingLoan = loanService.getLoanById(id);
		if (existingLoan == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		loan.setId(id);
		loanService.updateLoan(loan);
		return Response.ok(loan).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLoan(@PathParam("id") Long id) {
		Loan existingLoan = loanService.getLoanById(id);
		if (existingLoan == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		loanService.deleteLoan(id);
		return Response.noContent().build();
	}
}
