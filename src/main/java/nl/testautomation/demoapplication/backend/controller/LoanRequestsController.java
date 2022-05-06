package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.service.DemoGeneratorService;
import nl.testautomation.demoapplication.backend.service.LoanRequestService;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/${endpoints.requests}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanRequestsController {

    @Autowired
    LoanRequestService loanRequestService;
    @Autowired
    LoanTypeService loanTypeService;
    @Autowired
    DemoGeneratorService demoGeneratorService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public List<LoanRequest> getAllLoanRequests() {
        return loanRequestService.getAllLoanRequests();
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public ResponseEntity<LoanRequest> getLoanRequests(@PathVariable Integer id) {
        Optional<LoanRequest> loanRequest = loanRequestService.getLoanRequest(id);
        return ResponseEntity.of(loanRequest);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public ResponseEntity<String> deleteLoanRequests(@PathVariable Integer id) {
        loanRequestService.deleteLoanRequest(id);
        return ResponseEntity.ok().body("");
    }    

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('REQUEST_WRITE')")
    public ResponseEntity<LoanRequest> approve(@PathVariable Integer id) {
        return ResponseEntity.of(loanRequestService.approveLoanRequest(id));
    }    

    @PostMapping("/decline/{id}")
    @PreAuthorize("hasAuthority('REQUEST_WRITE')")
    public ResponseEntity<LoanRequest> decline(@PathVariable Integer id) {
        return ResponseEntity.of(loanRequestService.declineLoanRequest(id));
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
    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public ResponseEntity<String> clearAll() {
        loanRequestService.clearLoanRequests();
        return ResponseEntity.ok().body("Cleared all requestors");
    }
}
