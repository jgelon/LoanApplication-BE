package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.dto.LenderDto;
import nl.testautomation.demoapplication.backend.model.Lender;
import nl.testautomation.demoapplication.backend.repository.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LenderService {

    @Autowired
    LenderRepository lenderRepository;

    public List<Lender> getAllLenders() {
        List<Lender> lenders = new ArrayList<>();
        lenderRepository.findAll().forEach(lenders::add);
        return lenders;
    }

    public Lender addNewLender(LenderDto lenderDto) {
        Lender lender = new Lender()
                .setAmount(lenderDto.getAmount())
                .setFirstName(lenderDto.getFirstName())
                .setLastName(lenderDto.getLastName());
        return lenderRepository.save(lender);
    }
}
