package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.model.Loan;
import nl.testautomation.demoapplication.backend.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "*")
public class LoanController {
    @Autowired
    LoanService loanService;

    @GetMapping("/")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable("id") int id) {
        return loanService.getLoanById(id);
    }

//    @DeleteMapping("/{id}")
//    private void deleteLoan(@PathVariable("id") int id) {
//        loanService.delete(id);
//    }



}
