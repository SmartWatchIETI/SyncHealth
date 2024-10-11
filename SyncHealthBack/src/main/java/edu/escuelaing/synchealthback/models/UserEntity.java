package edu.escuelaing.synchealthback.models;

import edu.escuelaing.synchealthback.enums.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Model that represents a user
 * @author  Andres Arias
 */
@Data
@Document(collection = "users")
@NoArgsConstructor
public class UserEntity {

    @Id
    private String id;
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String hashPassword;
    @NotBlank
    @Max(10)
    private String phone;
    @NotBlank
    private String bornDate;
    @NotBlank
    private String city;
    @NotBlank
    private RoleEntity role;
    @NotBlank
    @Max(3)
    private String bloodGroup;
    @NotBlank
    @Max(3)
    private String weight;
    @NotBlank
    @Max(3)
    private String height;


    public UserEntity(String fullName, String email, String hashPassword, String bornDate) {
        this.fullName = fullName;
        this.email = email;
        this.hashPassword = hashPassword;
        this.bornDate = bornDate;
        this.role = RoleEntity.PATIENT;
        this.id = role.name() + UUID.randomUUID().toString();
    }
}
