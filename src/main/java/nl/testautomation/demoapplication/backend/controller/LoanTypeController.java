package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${endpoints.loantypes}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanTypeController {
    @Autowired
    LoanTypeService loanTypeService;

    @GetMapping("/")
    public List<LoanType> getAllLoanTypes() {
        return loanTypeService.getAllLoanTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanType> getLoan(@PathVariable("id") int id) {
        return ResponseEntity.of(loanTypeService.getLoanTypeById(id));
    }

}
