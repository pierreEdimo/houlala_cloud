package com.example.authenticationclient.decoder;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.model.DecodedMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AuthErrorDecoder implements ErrorDecoder {

    /**
     * Decode l'erreur venant d'un feign service.
     * @param methodKey
     * @param response
     * @return
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        DecodedMessage message;
        try (InputStream stream = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            message = mapper.readValue(stream, DecodedMessage.class);
        } catch (IOException ioException) {
            return new AuthenticationException(ioException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new AuthenticationException(message.getMessage(), Objects.requireNonNull(HttpStatus.resolve(response.status())));
    }
}
