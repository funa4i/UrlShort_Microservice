package org.urlshort.domain.data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Login can't be empty")
    private String login;

    @Email(message = "Email isn't valid")
    private String email;

    @NotBlank(message = "Password can't be empty")
    private String password;

    @NotBlank
    private String role;
}
