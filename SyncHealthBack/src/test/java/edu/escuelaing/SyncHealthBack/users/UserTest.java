package edu.escuelaing.SyncHealthBack.users;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.escuelaing.SyncHealthBack.models.User;
import edu.escuelaing.SyncHealthBack.enums.Role;


class UserTest {

    @Test
    void constructorShouldInitializeUserWithCorrectValues() {
        User user = new User("John Doe", "john.doe@example.com", "hashedPassword", "1990-01-01", "1");
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("hashedPassword", user.getHashPassword());
        assertEquals("1990-01-01", user.getBornDate());
        assertEquals("1", user.getId());
        assertEquals(Role.PATIENT, user.getRole());
    }

    @Test
    void defaultConstructorShouldInitializeUserWithDefaults() {
        User user = new User();
        assertNull(user.getFullName());
        assertNull(user.getEmail());
        assertNull(user.getHashPassword());
        assertNull(user.getBornDate());
        assertNull(user.getId());
        assertNull(user.getRole());
        assertNull(user.getBloodGroup());
        assertNull(user.getWeight());
        assertNull(user.getHeight());
    }

    @Test
    void settersAndGettersShouldWorkCorrectly() {
        User user = new User();
        user.setFullName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setHashPassword("newHashPassword");
        user.setBornDate("1992-02-02");
        user.setId("2");
        user.setCity("New York");
        user.setRole(Role.DOCTOR);
        user.setBloodGroup("O+");
        user.setWeight("60kg");
        user.setHeight("170cm");

        assertEquals("Jane Doe", user.getFullName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newHashPassword", user.getHashPassword());
        assertEquals("1992-02-02", user.getBornDate());
        assertEquals("2", user.getId());
        assertEquals("New York", user.getCity());
        assertEquals(Role.DOCTOR, user.getRole());
        assertEquals("O+", user.getBloodGroup());
        assertEquals("60kg", user.getWeight());
        assertEquals("170cm", user.getHeight());
    }

    @Test
    void userWithEmptyIdShouldBeValid() {
        User user = new User("John Doe", "john.doe@example.com", "hashedPassword", "1990-01-01", "");
        assertEquals("", user.getId());
    }
}
