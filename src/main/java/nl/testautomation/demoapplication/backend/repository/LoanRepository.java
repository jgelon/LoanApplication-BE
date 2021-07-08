package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Integer> {

}
