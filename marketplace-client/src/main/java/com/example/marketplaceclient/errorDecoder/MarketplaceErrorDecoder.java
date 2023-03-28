package com.example.marketplaceclient.errorDecoder;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.DecodeMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class MarketplaceErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        DecodeMessage message;
        try (InputStream stream = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            message = mapper.readValue(stream, DecodeMessage.class);
        } catch (IOException ioException) {
            return new MarketplaceException(ioException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new MarketplaceException(message.getMessage(), Objects.requireNonNull(HttpStatus.resolve(response.status())));
    }
}
