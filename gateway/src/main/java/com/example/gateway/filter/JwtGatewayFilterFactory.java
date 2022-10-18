package com.example.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;


@Component
@RequiredArgsConstructor
public class JwtGatewayFilterFactory implements GatewayFilterFactory<JwtGatewayFilterConfig> {

    private final WebClient.Builder webClientBuilder;

    @Override
    public Class<JwtGatewayFilterConfig> getConfigClass() {
        return JwtGatewayFilterConfig.class;
    }

    @Override
    public GatewayFilter apply(JwtGatewayFilterConfig config) {
        return (exchange, chain) -> {

            var headers = exchange.getRequest().getHeaders().getOrEmpty("Token");

            if (headers.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry mate, you need to add a Token into your request. ");
            }

            return webClientBuilder.build()
                    .get()
                    .uri("http://houlala_authentication:8000/api/User/ValidateToken/" + headers.get(0))
                    .retrieve().bodyToMono(String.class)
                    .map(str -> {
                        String response = str.substring(1, str.length() - 1);
                        if (response.equals("NotValidated")) {
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sorry mate, you have no business doing hier. ");
                        }
                        return exchange;
                    })
                    .flatMap(chain::filter);
        };
    }
}
