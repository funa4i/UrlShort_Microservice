package org.urlshort.domain.data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Login can't be empty")
    private String login;

    @Email(message = "Email isn't valid")
    @NotNull
    private String email;

    @NotBlank(message = "Password can't be empty")
    private String password;
}
