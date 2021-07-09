package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${endpoints.requests}")
@CrossOrigin(origins = "*")
public class LoanRequestsController {

    @Autowired
    LoanRequestService loanRequestService;

    @GetMapping("/")
    public List<LoanRequest> getAllLoanRequests() {
        return loanRequestService.getAllLoanRequests();
    }

    @PostMapping("/new")
    public LoanRequest addNewRequest(@RequestBody LoanRequestDto loanRequestDto) {
        return loanRequestService.addNewRequest(loanRequestDto);
    }
}
