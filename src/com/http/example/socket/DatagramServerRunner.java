package com.http.example.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServerRunner {

    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(12345)) {
            byte[] buffer = new byte[512];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);
            System.out.println(new String(buffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
