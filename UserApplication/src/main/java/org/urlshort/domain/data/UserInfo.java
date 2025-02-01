package org.urlshort.domain.data;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    @Email
    @NotNull
    private String email;

    @NotNull
    private Integer linksPerDay;

    @NotNull
    private Integer linksLeft;

    @NotNull
    private LocalDateTime dayCreate;
}
