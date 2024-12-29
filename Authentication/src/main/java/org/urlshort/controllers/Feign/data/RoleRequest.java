package org.urlshort.controllers.Feign.data;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleRequest {

    @NotBlank(message = "role can't be empty")
    private String role;
}
