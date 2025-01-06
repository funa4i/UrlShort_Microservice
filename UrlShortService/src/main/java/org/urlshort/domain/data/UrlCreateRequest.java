package org.urlshort.domain.data;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCreateRequest {

    @NotBlank
    @Pattern(regexp = "((http:\\/\\/)|(https:\\/\\/)).*")
    private String url;

    @NotBlank
    private Long iterations;

    @Min(1) @NotNull
    private Long userid;
}
