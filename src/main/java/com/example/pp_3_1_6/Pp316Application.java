package com.example.pp_3_1_6;

import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class Pp316Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Pp316Application.class, args);
    }
}