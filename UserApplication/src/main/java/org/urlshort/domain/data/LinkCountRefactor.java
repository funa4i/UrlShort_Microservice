package org.urlshort.domain.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LinkCountRefactor {
    @Min(1)
    @NotNull
    private Long userId;

    @Min(1)
    @NotNull
    private Integer linksCount;
}
