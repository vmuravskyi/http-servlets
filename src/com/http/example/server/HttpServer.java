package com.http.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             socket) {
            // handle request
            System.out.println("Request: " + new String(dataInputStream.readNBytes(400)));

            // handle response
            byte[] body = Files.readAllBytes(Path.of("src/resources/example.html"));
            String headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length);
            dataOutputStream.write(headers.getBytes(StandardCharsets.UTF_8));
            dataOutputStream.write(System.lineSeparator().getBytes());
            dataOutputStream.write(body);
        } catch (IOException e) {
            // TODO: 23/07/24 log error message
            throw new RuntimeException(e);
        }
    }

}
