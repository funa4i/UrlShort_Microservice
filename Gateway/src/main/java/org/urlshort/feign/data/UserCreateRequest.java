package org.urlshort.feign.data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateRequest {

    @Min(1)
    @NotNull
    private Long id;

    @Email
    @NotNull
    private String email;
}
