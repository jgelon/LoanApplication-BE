package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanTypeService {
    @Autowired
    LoanTypeRepository loanTypeRepository;

    public List<LoanType> getAllLoanTypes() {
        List<LoanType> loanTypes = new ArrayList<>();
        loanTypeRepository.findAll().forEach(loanTypes::add);
        return loanTypes;
    }

    public Optional<LoanType> getLoanTypeById(int id) {
        return loanTypeRepository.findById(id);
    }

    public void saveOrUpdate(LoanType type) {
        loanTypeRepository.save(type);
    }

    public void delete(int id) {
        loanTypeRepository.deleteById(id);
    }
}
