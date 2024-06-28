package controller;

import com.google.inject.Inject;
import model.Loan;
import service.LoanServiceImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/loans")
public class LoanController {
	private static final Logger logger = LogManager.getLogger(LoanController.class);
	
	private final LoanServiceImpl loanServiceImpl;

	@Inject
	public LoanController(LoanServiceImpl loanServiceImpl) {
		logger.info("LoanController inject");
		this.loanServiceImpl = loanServiceImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllLoans() {
		logger.info("Get all loans");
		return loanServiceImpl.getAllLoans();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoanById(@PathParam("id") Long id) {
		logger.info("Get loan for id");
		return loanServiceImpl.getLoanById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLoan(Loan loan) {
		logger.info("Add loan");
		return loanServiceImpl.addLoan(loan);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateLoan(@PathParam("id") Long id, Loan loan) {
		logger.info("Update loan");
		return loanServiceImpl.updateLoan(id, loan);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteLoan(@PathParam("id") Long id) {
		logger.info("Delete loan");
		return loanServiceImpl.deleteLoan(id);
	}
}
