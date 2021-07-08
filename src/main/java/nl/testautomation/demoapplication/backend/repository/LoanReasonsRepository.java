package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.LoanReason;
import org.springframework.data.repository.CrudRepository;

public interface LoanReasonsRepository extends CrudRepository<LoanReason, Integer> {

}
