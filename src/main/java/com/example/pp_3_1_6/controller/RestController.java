package com.example.pp_3_1_6.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import model.User;
import org.apache.catalina.session.StandardSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
    RestTemplate restTemplate;
    RestTemplate postRestTemplate;

    @GetMapping
    public String getAllUsers() {

        ResponseEntity<String> responseEntity1 = restTemplate
                .getForEntity("http://94.198.50.185:7081/api/users", String.class);
        String cookie = responseEntity1.getHeaders().get("Set-Cookie").toString();
        cookie = cookie.substring(1);
        cookie = cookie.substring(0, cookie.length() - 1);
        System.out.println(cookie);

        User user = new User(3L, "James", "Brown", (byte) 40);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity2 = restTemplate
                .exchange("http://94.198.50.185:7081/api/users", HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseEntity2.getBody());

        user.setName("Thomas");
        user.setLastName("Shelby");
        ResponseEntity<String> responseEntity3 = restTemplate
                .exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, httpEntity, String.class);
        System.out.println(responseEntity3.getBody());

        ResponseEntity<String> responseEntity4 = restTemplate
                .exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(responseEntity4.getBody());


        //responseEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);


        /*User user = new User(3L, "James", "Brown", (byte) 40);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate
        .exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, httpEntity, String.class);*/
//------------------------------------
        //Получаем заголовки от responseEntity
        /*String cookie = responseEntity.getHeaders().get("Set-cookie").toString();
        /*cookie = cookie.substring(1);
        cookie = cookie.substring(0, cookie.length() - 1);*/
        /*System.out.println("заголовки от responseEntity: " + cookie);

        //Устанавливаем заголовкки
        httpHeaders.add("Set-Cookie", cookie);
        HttpEntity<User> httpEntity2 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> postEntity = restTemplate
                .exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, httpEntity2, String.class);

        //Получаем заголовки от posEntity
        System.out.println("заголовки от postEntity: " + postEntity.getHeaders().get("Set-cookie").toString());*/

        return responseEntity1.getBody();
    }
    /*@PostMapping
    public String addNewUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }*/

}
