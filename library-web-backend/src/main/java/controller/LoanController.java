package controller;

import com.google.inject.Inject;

import model.Loan;
import service.LoanServiceImpl;

import java.util.List;

import javax.ws.rs.*;

@Path("/loans")
public class LoanController {
	private final LoanServiceImpl loanService;

	@Inject
	public LoanController(LoanServiceImpl loanService) {
		this.loanService = loanService;
	}

	@GET
	public List<Loan> getAllLoans() {
		return loanService.getAllLoans();
	}

	@GET
	@Path("/{id}")
	public Loan getLoanById(@PathParam("id") Long id) {
		return loanService.getLoanById(id);
		
	}

	@POST
	public void addLoan(Loan loan) {
		loanService.addLoan(loan);
	
	}

	@PUT
	@Path("/{id}")
	public void updateLoan(@PathParam("id") Long id, Loan loan) {
		Loan existingLoan = loanService.getLoanById(id);
		loan.setId(id);
		loanService.updateLoan(loan);
	}

	@DELETE
	@Path("/{id}")
	public void deleteLoan(@PathParam("id") Long id) {
		Loan existingLoan = loanService.getLoanById(id);
		loanService.deleteLoan(id);
	}
}
