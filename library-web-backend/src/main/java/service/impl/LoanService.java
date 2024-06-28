package service.impl;

import model.Loan;

public interface LoanService {
	String getLoanById(Long id);
	String getAllLoans();
	String addLoan(Loan loan);
	String updateLoan(Long id, Loan loan);
	String deleteLoan(Long id);
}
