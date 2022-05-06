package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.Privilege;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String privilege);
}
