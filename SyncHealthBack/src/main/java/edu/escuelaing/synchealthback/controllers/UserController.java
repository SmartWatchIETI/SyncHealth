package edu.escuelaing.synchealthback.controllers;


import edu.escuelaing.synchealthback.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.escuelaing.synchealthback.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
/**
 * controller that manages the users
 * @author  Andres Arias
 */
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user
     * @param user  the user to create
     * @return  HttpStatus.CREATED if the user was created successfully
     */
    @PostMapping(value = "/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        User newUser = new User(user.getFullName(), user.getEmail(), user.getHashPassword(), user.getBornDate(), user.getId());
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Updates a user
     * @param user  the user to update
     * @return  HttpStatus.OK if the user was updated successfully
     */
    @PutMapping(value = "/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
        Optional<User> optionalUser = userService.findById(user.getId());
        if(optionalUser.isPresent()){
            User userToUpdate = optionalUser.get();
            userToUpdate.setCity(user.getCity());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setBloodGroup(user.getBloodGroup());
            userToUpdate.setHeight(user.getHeight());
            userToUpdate.setWeight(user.getWeight());
            userService.saveUser(userToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Gets the user info
     * @param id  the id of the user
     * @return user  the user with the given id
     */
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            User userToUpdate = optionalUser.get();
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Deletes a user
     * @param id  the id of the user
     * @return  HttpStatus.OK if the user was deleted successfully
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
