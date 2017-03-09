package io.smartbudget.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

public class HTTPTokenClientFilter implements ClientRequestFilter {

    private final String token;

    public HTTPTokenClientFilter(String token) {
        this.token = token;
    }

    @Override
    public void filter(ClientRequestContext crc) throws IOException {
        crc.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
