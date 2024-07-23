package com.http.example.server;

public class HttpServerRunner {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(12345);
        httpServer.run();
    }

}
