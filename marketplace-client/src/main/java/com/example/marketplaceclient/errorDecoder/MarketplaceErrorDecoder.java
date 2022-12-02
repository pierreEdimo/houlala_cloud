package com.example.marketplaceclient.errorDecoder;

import com.example.marketplaceclient.exception.MarketplaceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;


public class MarketplaceErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new MarketplaceException("sorry By calling the Products there were an error",
                HttpStatus.resolve(response.status()));
    }
}
