package org.urlshort.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Path {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "path_role",
            joinColumns = @JoinColumn(name = "path_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
