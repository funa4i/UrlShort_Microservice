package org.urlshort.feign.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlView {
    private String shortUrl;
    private String fullUrl;
    private Integer iterations;
    private LocalDateTime validUntil;
    private Long userid;
}
