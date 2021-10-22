package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.service.LoanReasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${endpoints.loanreasons}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanReasonController {
    @Autowired
    LoanReasonsService loanReasonsService;

    @GetMapping("/")
    public List<LoanReason> getAllLoanReasons() {
        return loanReasonsService.getAllReasons();
    }

}
