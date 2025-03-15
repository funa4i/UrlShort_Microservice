package org.urlshort.domain.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredLinkInfo {
    String email;
    String fullUrl;
    String shortUrl;
}
