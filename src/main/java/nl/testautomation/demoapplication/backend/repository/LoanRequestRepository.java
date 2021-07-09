package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.LoanRequest;
import org.springframework.data.repository.CrudRepository;

public interface LoanRequestRepository extends CrudRepository<LoanRequest, Integer> {

}
