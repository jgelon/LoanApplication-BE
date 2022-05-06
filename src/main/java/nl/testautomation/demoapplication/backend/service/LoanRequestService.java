package nl.testautomation.demoapplication.backend.service;

import java.util.Optional;
import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.enums.Decision;
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
        loanRequest.setDecision(Decision.OPEN);
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

    public Optional<LoanRequest> approveLoanRequest(Integer id) {
        Optional<LoanRequest> requestOptional = getLoanRequest(id);
        if(requestOptional.isPresent()){
            LoanRequest request = requestOptional.get();
            request.setDecision(Decision.APPROVED);
            return Optional.of(loanRequestRepository.save(request));
        }
        return Optional.empty();
    }

    public Optional<LoanRequest> declineLoanRequest(Integer id) {
        Optional<LoanRequest> requestOptional = getLoanRequest(id);
        if(requestOptional.isPresent()){
            LoanRequest request = requestOptional.get();
            request.setDecision(Decision.DECLINED);
            return Optional.of(loanRequestRepository.save(request));
        }
        return Optional.empty();
    }
}
