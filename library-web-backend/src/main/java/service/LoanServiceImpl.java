package service;

import dao.LoanDAO;
import model.Loan;
import service.impl.LoanService;
import com.google.inject.Inject;

public class LoanServiceImpl implements LoanService {
	
    private LoanDAO loanDAO;

    @Inject
    public LoanServiceImpl(LoanDAO loanDAO) {
    	this.loanDAO = loanDAO;
    }
    
    @Override
    public String getLoanById(Long id) {
        return loanDAO.findById(id);
    }

    @Override
    public String getAllLoans() {
        return loanDAO.findAll();
    }

    @Override
    public String addLoan(Loan loan) {
        return loanDAO.save(loan);
    }

    @Override
    public String updateLoan(Long id, Loan loan) {
        String existingLoan = loanDAO.findById(id);
        if (existingLoan != null) {
            loan.setId(id);
            return loanDAO.update(loan);
        } else {
            return "{\"status\":\"error\",\"message\":\"Loan not found\"}";
        }
    }

    @Override
    public String deleteLoan(Long id) {
        return loanDAO.delete(id);
    }
}

