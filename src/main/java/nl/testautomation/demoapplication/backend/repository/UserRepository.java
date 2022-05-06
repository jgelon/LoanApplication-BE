package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
