package com.http.example.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketRunner {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(inetAddress, 12345);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                System.out.println("Response form server: " + dataInputStream.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
