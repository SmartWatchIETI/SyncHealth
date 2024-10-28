package edu.escuelaing.synchealthback.controllers;


import edu.escuelaing.synchealthback.models.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user
     * @param user  the user to create
     * @return  HttpStatus.CREATED if the user was created successfully
     */
    @Operation(summary = "Create a new user", description = "This endpoint creates a new user.")
    @ApiResponse(responseCode = "201", description = "User created successfully.", content = @Content)
    @ApiResponse(responseCode ="409", description = "User already exists.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
    @PostMapping(value = "/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserEntity user){
        if(!userService.isPresent(user.getEmail())) {
            UserEntity newUser = new UserEntity(user.getFullName(), user.getEmail(), passwordEncoder.encode(user.getHashPassword()), user.getBornDate());
            userService.saveUser(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates a user
     * @param user  the user to update
     * @return  HttpStatus.OK if the user was updated successfully
     */
    @Operation(summary = "Update a user", description = "This endpoint updates a user by email.")
    @ApiResponse(responseCode = "200", description = "User updated successfully.", content = @Content)
    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
    @PutMapping(value = "/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserEntity user){
        Optional<UserEntity> optionalUser = userService.findById(user.getId());
        if(optionalUser.isPresent()){
            UserEntity userToUpdate = optionalUser.get();
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
     * @param email  the email of the user
     * @return user  the user with the given id
     */
    @Operation(summary = "Get a specific user", description = "This endpoint returns a specific user by email.")
    @ApiResponse(responseCode = "200", description = "Return the user.", content = @Content)
    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content)
    @GetMapping(value = "/get/{email}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String email){
        Optional<UserEntity> optionalUser = userService.findByEmail(email);
        if(optionalUser.isPresent()){
            UserEntity userToUpdate = optionalUser.get();
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Deletes a user
     * @param id  the id of the user
     * @return  HttpStatus.OK if the user was deleted successfully
     */
    @Operation(summary = "Delete a user", description = "This endpoint deletes a user by email.")
    @ApiResponse(responseCode = "200", description = "User deleted successfully.", content = @Content)
    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
        Optional<UserEntity> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
