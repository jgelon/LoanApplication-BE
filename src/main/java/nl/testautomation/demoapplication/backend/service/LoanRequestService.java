package nl.testautomation.demoapplication.backend.service;

import lombok.AllArgsConstructor;
import nl.testautomation.demoapplication.backend.dto.ErrorDto;
import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.enums.Decision;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanRequestService {

    private LoanRequestRepository loanRequestRepository;
    private LoanTypeService loanTypeService;

    public List<LoanRequest> getAllLoanRequests() {
        List<LoanRequest> loanRequests = new ArrayList<>();
        loanRequestRepository.findAll().forEach(loanRequests::add);
        return loanRequests;
    }

    public ResponseEntity<Object> addNewRequest(LoanRequestDto loanRequestDto) {
        LoanType loanType = loanTypeService.getLoanTypeById(loanRequestDto.getLoanTypeId()).orElseThrow();

        if(loanType.getMinAmount() > loanRequestDto.getAmount()) {
            return ResponseEntity.badRequest().body(new ErrorDto(601, "The requested amount is to low for this type of loan!"));
        }

        LoanRequest loanRequest = loanRequestDto.toLoanRequest();
        loanRequest.setDecision(Decision.OPEN);
        loanRequest.setLoanType(loanType);
        return ResponseEntity.ok(loanRequestRepository.save(loanRequest));
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
