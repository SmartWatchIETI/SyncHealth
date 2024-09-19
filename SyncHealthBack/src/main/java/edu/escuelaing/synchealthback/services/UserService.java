package edu.escuelaing.synchealthback.services;

import edu.escuelaing.synchealthback.models.User;
import edu.escuelaing.synchealthback.repositorys.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

/**
 * Service that manages the users
 * @author  Andres Arias
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user
     * @param user  the user to save
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Finds a user by its id
     * @param id  the id of the user
     * @return  the user with the given id
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Deletes a user
     * @param id  the id of the user
     */
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
