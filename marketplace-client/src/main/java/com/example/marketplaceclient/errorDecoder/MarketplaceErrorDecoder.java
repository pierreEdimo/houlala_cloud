package com.example.marketplaceclient.errorDecoder;


import com.example.marketplaceclient.exception.MarketplaceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class MarketplaceErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return new MarketplaceException("Error by Calling the MarketPlace-Service",
                HttpStatus.resolve(response.status()));
    }
}
