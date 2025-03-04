package org.urlshort.feign.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessCheckAnswer {
    public Boolean access;
}
