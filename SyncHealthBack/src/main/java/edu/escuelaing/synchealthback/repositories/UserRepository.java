package edu.escuelaing.synchealthback.repositories;

import edu.escuelaing.synchealthback.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository that manages the users
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByFullName(String username);

    Optional<UserEntity> findByEmail(String username);
}
