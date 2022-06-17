package com.example.orchestrator.errordecoder;

import com.example.orchestrator.exception.OrchestratorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CountryErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String errorMessage = this.getErrorMessage(response);

        return new OrchestratorException(errorMessage, HttpStatus.resolve(response.status()));
    }

    private String getErrorMessage(Response response) {
        String defaultErrorMessage = "Beim Aufrufen des Country-Service ist ein unerwarteter Fehler aufgetreten. ";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseString = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8.name());
            Map<String, String> errorMap = objectMapper.readValue(responseString, new TypeReference<Map<String, String>>() {
            });

            if (!errorMap.containsKey("errorMessage")) {
                return defaultErrorMessage;
            }

            return errorMap.get("errorMessage");
        } catch (IOException ex) {
            return defaultErrorMessage;
        }

    }
}
