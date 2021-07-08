package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LenderDto;
import nl.testautomation.demoapplication.backend.model.Lender;
import nl.testautomation.demoapplication.backend.service.LenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lenders")
@CrossOrigin(origins = "*")
public class LenderController {

    @Autowired
    LenderService lenderService;

    @GetMapping("/")
    public List<Lender> getAllLenders() {
        return lenderService.getAllLenders();
    }

    @PostMapping("/new")
    public Lender addNewLender(@RequestBody LenderDto lenderDto) {
        return lenderService.addNewLender(lenderDto);
    }
}
