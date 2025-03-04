package org.urlshort.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private RequestMethod method;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "paths",fetch = FetchType.LAZY)
    private List<Role> roles;

    public Path(String path){
        name = path;
    }
}
