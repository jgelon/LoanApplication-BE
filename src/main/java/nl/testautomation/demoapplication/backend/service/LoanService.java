package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.Loan;
import nl.testautomation.demoapplication.backend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        loanRepository.findAll().forEach(loans::add);
        return loans;
    }

    public Loan getLoanById(int id) {
        return loanRepository.findById(id).get();
    }

    public void saveOrUpdate(Loan person) {
        loanRepository.save(person);
    }

    public void delete(int id) {
        loanRepository.deleteById(id);
    }
}
