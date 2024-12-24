package org.urlshort.domain.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Path> paths;
}
