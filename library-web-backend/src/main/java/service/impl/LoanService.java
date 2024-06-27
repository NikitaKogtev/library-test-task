package service.impl;

import model.Loan;
import java.util.List;

public interface LoanService {
	Loan getLoanById(Long id);
	List<Loan> getAllLoans();
	void addLoan(Loan loan);
	void updateLoan(Loan loan);
	void deleteLoan(Long id);
}
