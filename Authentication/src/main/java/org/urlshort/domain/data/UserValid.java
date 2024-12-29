package org.urlshort.domain.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserValid {

    @NotBlank(message = "Login can't be empty")
    private String login;

    @NotBlank(message = "Password can't be empty")
    private String password;

}
