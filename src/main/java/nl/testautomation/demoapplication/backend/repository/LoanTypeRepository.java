package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.LoanType;
import org.springframework.data.repository.CrudRepository;

public interface LoanTypeRepository extends CrudRepository<LoanType, Integer> {

}
