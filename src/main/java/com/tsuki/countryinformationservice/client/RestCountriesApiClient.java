package com.tsuki.countryinformationservice.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Component;

@Component
public class RestCountriesApiClient implements ApiClient {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @Override
    public String getPage(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> httpResponse = HTTP_CLIENT
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return httpResponse.body();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
