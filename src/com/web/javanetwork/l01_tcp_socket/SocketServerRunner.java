package com.web.javanetwork.l01_tcp_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345);
             Socket socket = serverSocket.accept();
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            String request = dataInputStream.readUTF();
            while (!request.equals("stop")) {
                System.out.println("Client request: " + request);
                String response = scanner.nextLine();
                dataOutputStream.writeUTF(response);
                request = dataInputStream.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
