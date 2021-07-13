package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.service.DemoGeneratorService;
import nl.testautomation.demoapplication.backend.service.LoanRequestService;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<LoanRequest> getLoanRequests(@PathVariable Integer id) {
        Optional<LoanRequest> loanRequest = loanRequestService.getLoanRequest(id);
        return ResponseEntity.of(loanRequest);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteLoanRequests(@PathVariable Integer id) {
        loanRequestService.deleteLoanRequest(id);
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/new")
    public LoanRequest addNewRequest(@RequestBody LoanRequestDto loanRequestDto) {
        return loanRequestService.addNewRequest(loanRequestDto);
    }

    @GetMapping("/generate")
    public List<LoanRequest> generate() {
        return demoGeneratorService.generateLoanRequests(1);
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearAll() {
        loanRequestService.clearLoanRequests();
        return ResponseEntity.ok().body("Cleared all requestors");
    }
}
