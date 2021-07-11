package nl.testautomation.demoapplication.backend.service;

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
    LoanRequestRepository loanRequestRepository;

    public List<LoanRequest> getAllLoanRequests() {
        List<LoanRequest> loanRequests = new ArrayList<>();
        loanRequestRepository.findAll().forEach(loanRequests::add);
        return loanRequests;
    }

    public LoanRequest addNewRequest(LoanRequestDto loanRequestDto) {

        return loanRequestRepository.save(loanRequestDto.toLoanRequest());
    }

    public void clearLoanRequests() {
        loanRequestRepository.deleteAll();
    }
}
