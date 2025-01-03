package org.urlshort.domain.entity;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "links_per_day", nullable = false)
    private Integer linksPerDay;

    @Column(name = "links_left", nullable = false)
    private Integer linksLeft;

    @CreationTimestamp
    @Column(name = "day_create", nullable = false)
    private LocalDateTime dayCreate;
}
