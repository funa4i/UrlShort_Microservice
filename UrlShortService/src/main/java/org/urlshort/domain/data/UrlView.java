package org.urlshort.domain.data;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.urlshort.domain.entities.User;

import java.time.LocalDateTime;

@Data
public class UrlView {


    private String shortUrl;

    private String fullUrl;

    private Integer iterations;

    private LocalDateTime validUntil;

    private Long userid;
}
