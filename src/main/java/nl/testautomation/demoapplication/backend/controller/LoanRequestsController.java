package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.service.DemoGeneratorService;
import nl.testautomation.demoapplication.backend.service.LoanRequestService;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${endpoints.requests}")
@CrossOrigin(origins = "*")
public class LoanRequestsController {

    @Autowired
    LoanRequestService loanRequestService;
    @Autowired
    LoanTypeService loanTypeService;
    @Autowired
    DemoGeneratorService demoGeneratorService;

    @GetMapping("/")
    public List<LoanRequest> getAllLoanRequests() {
        return loanRequestService.getAllLoanRequests();
    }

    @PostMapping("/new")
    public LoanRequest addNewRequest(@RequestBody LoanRequestDto loanRequestDto) {
        return loanRequestService.addNewRequest(loanRequestDto);
    }

    @GetMapping("/generate")
    public List<LoanRequest> generate() {
        return demoGeneratorService.generateLoanRequests(5);
    }
}
