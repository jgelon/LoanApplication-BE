package nl.testautomation.demoapplication.backend.service;

import java.util.Optional;
import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanRequestService {

    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private LoanTypeService loanTypeService;

    public List<LoanRequest> getAllLoanRequests() {
        List<LoanRequest> loanRequests = new ArrayList<>();
        loanRequestRepository.findAll().forEach(loanRequests::add);
        return loanRequests;
    }

    public LoanRequest addNewRequest(LoanRequestDto loanRequestDto) {
        LoanRequest loanRequest = loanRequestDto.toLoanRequest();
        loanRequest.setLoanType(loanTypeService.getLoanTypeById(loanRequestDto.getLoanTypeId()));
        return loanRequestRepository.save(loanRequest);
    }

    public void clearLoanRequests() {
        loanRequestRepository.deleteAll();
    }

    public Optional<LoanRequest> getLoanRequest(Integer id) {
        return loanRequestRepository.findById(id);
    }

    public void deleteLoanRequest(Integer id) {
        loanRequestRepository.deleteById(id);
    }
}
