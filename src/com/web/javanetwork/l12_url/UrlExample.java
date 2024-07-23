package com.web.javanetwork.l12_url;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://google.com");
        URLConnection urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }

}
