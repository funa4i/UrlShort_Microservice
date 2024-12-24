package org.urlshort.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @OneToOne
    private Role role;
}
