package org.urlshort.domain.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkInfo {
    String userEmail;
    Long createAmount;
    LocalDateTime date;
}
