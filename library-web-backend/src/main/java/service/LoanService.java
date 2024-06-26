package service;

import dao.LoanDAO;
import model.Loan;

import java.util.List;

import com.google.inject.Inject;

public class LoanService {
    private LoanDAO loanDAO;

    @Inject
    public LoanService(LoanDAO loanDAO) {
    	this.loanDAO = loanDAO;
    }
    
    // Найти запись о займе по ID
    public Loan getLoanById(Long id) {
        return loanDAO.findById(id);
    }

    // Найти все записи о займах
    public List<Loan> getAllLoans() {
        return loanDAO.findAll();
    }

    // Добавить запись о займе
    public void addLoan(Loan loan) {
        loanDAO.save(loan);
    }

    // Обновить запись о займе
    public void updateLoan(Loan loan) {
        loanDAO.update(loan);
    }

    // Удалить запись о займе
    public void deleteLoan(Long id) {
        loanDAO.delete(id);
    }

    // Закрыть ресурсы
    public void close() {
        loanDAO.close();
    }
}

