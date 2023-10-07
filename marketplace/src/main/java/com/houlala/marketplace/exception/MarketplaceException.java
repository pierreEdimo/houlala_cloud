package com.houlala.marketplace.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class MarketplaceException extends ResponseStatusException {
    public MarketplaceException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
