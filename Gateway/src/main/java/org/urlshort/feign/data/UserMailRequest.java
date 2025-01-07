package org.urlshort.feign.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserMailRequest {

    @Email
    @NotNull
    private String email;

}
