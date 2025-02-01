package org.urlshort.domain.entities;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", length = 7, nullable = false)
    private String shortUrl;

    @Column(name = "full_url", nullable = false)
    private String fullUrl;

    @Column(name = "iterations", nullable = false)
    private Integer iterations;

    @CreationTimestamp
    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getUserId(){
        return user.getId();
    }
}
