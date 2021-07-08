package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanReasonsService {
    @Autowired
    LoanReasonsRepository loanReasonsRepository;

    public List<LoanReason> getAllReasons() {
        List<LoanReason> reasons = new ArrayList<>();
        loanReasonsRepository.findAll().forEach(reasons::add);
        return reasons;
    }

    public void saveOrUpdate(LoanReason reason) {
        loanReasonsRepository.save(reason);
    }

    public void delete(int id) {
        loanReasonsRepository.deleteById(id);
    }
}
