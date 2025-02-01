package org.urlshort.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.urlshort.domain.entities.Path;
import org.urlshort.domain.repositories.PathRepository;
import org.urlshort.exceptions.NullObjectException;

@Component
@RequiredArgsConstructor
public class PathManager {
    private final PathRepository pathRepository;

    public Path findByName(String pathName){
        return pathRepository.findByName(pathName)
                .orElseThrow(() -> new NullObjectException(Path.class, pathName));
    }
}
