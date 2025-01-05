package org.urlshort.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.urlshort.advice.ExceptionMessage;
import org.urlshort.domain.exceptions.NotFoundException;
import org.urlshort.domain.exceptions.UncheckedException;

import java.io.IOException;
import java.io.InputStream;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {

            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return switch (response.status()) {
            case 404 -> new NotFoundException(message.getMessage());
            default -> new UncheckedException(message.getMessage());
        };
    }
}
