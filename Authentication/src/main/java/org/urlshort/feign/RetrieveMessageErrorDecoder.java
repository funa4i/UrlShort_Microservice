package org.urlshort.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.urlshort.advice.ExceptionView;
import org.urlshort.exceptions.BadRequestException;
import org.urlshort.exceptions.NotFoundException;
import org.urlshort.exceptions.UncheckedException;

import java.io.IOException;
import java.io.InputStream;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionView message = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionView.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 404 -> new NotFoundException(message.getMessage());
            case 400 -> new BadRequestException(message.getMessage());
            default -> new UncheckedException(message.getMessage());
        };
    }
}
