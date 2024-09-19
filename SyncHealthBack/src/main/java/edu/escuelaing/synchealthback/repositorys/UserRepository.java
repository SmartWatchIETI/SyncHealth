package edu.escuelaing.synchealthback.repositorys;

import edu.escuelaing.synchealthback.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the users
 * @author  Andres Arias
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
