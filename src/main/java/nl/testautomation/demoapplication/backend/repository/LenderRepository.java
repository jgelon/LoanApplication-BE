package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.Lender;
import org.springframework.data.repository.CrudRepository;

public interface LenderRepository extends CrudRepository<Lender, Integer> {

}
