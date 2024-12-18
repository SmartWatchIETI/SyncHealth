package edu.escuelaing.synchealthback;

import edu.escuelaing.synchealthback.services.UserService;
import edu.escuelaing.synchealthback.models.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private edu.escuelaing.synchealthback.controllers.UserController userController;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new UserEntity("Full Name", "email@example.com", "hashedPassword", "1990-01-01");
    }

    /**
     * Tests that a user is created successfully and returns a CREATED status.
     */
    @Test
    void createUser_shouldReturnCreated() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        ResponseEntity<HttpStatus> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).saveUser(any(UserEntity.class));
    }

    /**
     * Tests that the updateUser method returns OK when the user exists and is updated successfully.
     */
    @Test
    void updateUser_shouldReturnOk_whenUserExists() {
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        user.setCity("New City");

        ResponseEntity<HttpStatus> response = userController.updateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New City", user.getCity());
        verify(userService, times(1)).saveUser(user);
    }

    /**
     * Tests that the updateUser method returns NOT_FOUND when the user does not exist.
     */
    @Test
    void updateUser_shouldReturnNotFound_whenUserDoesNotExist() {
        when(userService.findById(user.getId())).thenReturn(Optional.empty());

        ResponseEntity<HttpStatus> response = userController.updateUser(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, never()).saveUser(any(UserEntity.class));
    }

    /**
     * Tests that the getUser method returns the user and an OK status when the user exists.
     */
    @Test
    void getUser_shouldReturnUser_whenUserExists() {
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<UserEntity> response = userController.getUser(user.getEmail());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    /**
     * Tests that the getUser method returns NOT_FOUND when the user does not exist.
     */
    @Test
    void getUser_shouldReturnNotFound_whenUserDoesNotExist() {
        when(userService.findById(user.getId())).thenReturn(Optional.empty());

        ResponseEntity<UserEntity> response = userController.getUser(user.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Tests that the deleteUser method returns OK when the user exists and is deleted successfully.
     */
    @Test
    void deleteUser_shouldReturnOk_whenUserExists() {
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<HttpStatus> response = userController.deleteUser(user.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).deleteUser(user.getId());
    }

    /**
     * Tests that the deleteUser method returns NOT_FOUND when the user does not exist.
     */
    @Test
    void deleteUser_shouldReturnNotFound_whenUserDoesNotExist() {
        when(userService.findById(user.getId())).thenReturn(Optional.empty());

        ResponseEntity<HttpStatus> response = userController.deleteUser(user.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, never()).deleteUser(user.getId());
    }
}
