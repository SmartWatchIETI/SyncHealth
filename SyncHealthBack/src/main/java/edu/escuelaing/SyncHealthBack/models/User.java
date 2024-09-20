package edu.escuelaing.SyncHealthBack.models;

import edu.escuelaing.SyncHealthBack.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model that represents a user
 * @author  Andres Arias
 */
@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    private String fullName;
    private String email;
    private String hashPassword;
    private String phone;
    private String bornDate;
    private String city;
    private Role role;
    private String id;
    private String bloodGroup;
    private String weight;
    private String height;


    public User(String fullName, String email, String hashPassword, String bornDate, String id) {
        this.fullName = fullName;
        this.email = email;
        this.hashPassword = hashPassword;
        this.bornDate = bornDate;
        this.role = role.PATIENT;
        this.id = id;
    }
}
