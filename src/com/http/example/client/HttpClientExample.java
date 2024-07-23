package com.http.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        try (HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build()) {

            HttpRequest getRequest = HttpRequest.newBuilder(URI.create("https://google.com"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.headers());

            HttpRequest postRequest = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("path", "to", "file")))
                    .header("content-type", "text/plain")
                    .build();
        }
    }

}
