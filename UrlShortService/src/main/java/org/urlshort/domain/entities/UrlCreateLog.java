package org.urlshort.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jdk.jfr.Timestamp;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class UrlCreateLog {
    @EmbeddedId
    private UrlCreateLogId urlCreateLogId;

    @Timestamp
    @Column(name = "timeCreate")
    private LocalDateTime dateTime;
}
