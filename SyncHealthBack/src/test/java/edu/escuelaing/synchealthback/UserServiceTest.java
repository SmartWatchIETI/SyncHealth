package edu.escuelaing.synchealthback;

import edu.escuelaing.synchealthback.models.UserEntity;
import edu.escuelaing.synchealthback.repositories.UserRepository;
import edu.escuelaing.synchealthback.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new UserEntity("Full Name", "email@example.com", "hashedPassword", "1990-01-01");
    }

    /**
     * Tests that the saveUser method calls the userRepository save method.
     */
    @Test
    void saveUser_shouldCallUserRepositorySave() {
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Tests that the findById method returns a user when the user exists.
     */
    @Test
    void findById_shouldReturnUser_whenUserExists() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<UserEntity> foundUser = userService.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    /**
     * Tests that the findById method returns an empty Optional when the user does not exist.
     */
    @Test
    void findById_shouldReturnEmpty_whenUserDoesNotExist() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        Optional<UserEntity> foundUser = userService.findById(user.getId());
        assertFalse(foundUser.isPresent());
    }

    /**
     * Tests that the deleteUser method calls the userRepository deleteById method.
     */
    @Test
    void deleteUser_shouldCallUserRepositoryDeleteById() {
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    /**
     * Tests that saveUser does nothing if the user is null.
     */
    @Test
    void saveUser_shouldNotCallRepositoryIfUserIsNull() {
        userService.saveUser(null);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    /**
     * Tests that findById returns empty Optional for invalid id.
     */
    @Test
    void findById_shouldReturnEmptyForInvalidId() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        Optional<UserEntity> foundUser = userService.findById("invalid_id");
        assertFalse(foundUser.isPresent());
    }

    /**
     * Tests that deleteUser does nothing if the id is null.
     */
    @Test
    void deleteUser_shouldNotCallRepositoryIfIdIsNull() {
        userService.deleteUser(null);
        verify(userRepository, never()).deleteById(anyString());
    }

    /**
     * Tests that saveUser is called with correct user object.
     */
    @Test
    void saveUser_shouldCallUserRepositoryWithCorrectUser() {
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Tests that findById returns empty if repository returns empty for non-existing user.
     */
    @Test
    void findById_shouldReturnEmptyIfRepositoryReturnsEmpty() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        Optional<UserEntity> foundUser = userService.findById(user.getId());
        assertFalse(foundUser.isPresent());
    }

    /**
     * Tests that findById handles non-String IDs correctly.
     */
    @Test
    void findById_shouldHandleNonStringId() {
        Optional<UserEntity> foundUser = userService.findById("non_string_id");
        assertFalse(foundUser.isPresent());
    }

    /**
     * Tests that deleteUser handles invalid IDs gracefully.
     */
    @Test
    void deleteUser_shouldHandleInvalidId() {
        userService.deleteUser("invalid_id");
        verify(userRepository, times(1)).deleteById("invalid_id");
    }
}
