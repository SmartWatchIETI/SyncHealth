package edu.escuelaing.synchealthback;

import edu.escuelaing.synchealthback.enums.RoleEntity;
import edu.escuelaing.synchealthback.models.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {
    @Test
    void constructorShouldInitializeUserWithCorrectValues() {
        UserEntity user = new UserEntity("John Doe", "john.doe@example.com", "hashedPassword", "1990-01-01");
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("hashedPassword", user.getHashPassword());
        assertEquals("1990-01-01", user.getBornDate());
        assertEquals(RoleEntity.PATIENT, user.getRole());
    }

    @Test
    void defaultConstructorShouldInitializeUserWithDefaults() {
        UserEntity user = new UserEntity();
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
        UserEntity user = new UserEntity();
        user.setFullName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setHashPassword("newHashPassword");
        user.setBornDate("1992-02-02");
        user.setId("2");
        user.setCity("New York");
        user.setRole(RoleEntity.DOCTOR);
        user.setBloodGroup("O+");
        user.setWeight("60kg");
        user.setHeight("170cm");

        assertEquals("Jane Doe", user.getFullName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newHashPassword", user.getHashPassword());
        assertEquals("1992-02-02", user.getBornDate());
        assertEquals("2", user.getId());
        assertEquals("New York", user.getCity());
        assertEquals(RoleEntity.DOCTOR, user.getRole());
        assertEquals("O+", user.getBloodGroup());
        assertEquals("60kg", user.getWeight());
        assertEquals("170cm", user.getHeight());
    }

}
