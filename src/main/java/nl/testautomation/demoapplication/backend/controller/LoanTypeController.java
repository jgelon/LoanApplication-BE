package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LoanType getLoan(@PathVariable("id") int id) {
        return loanTypeService.getLoanTypeById(id);
    }

//    @DeleteMapping("/{id}")
//    private void deleteLoan(@PathVariable("id") int id) {
//        loanService.delete(id);
//    }



}
