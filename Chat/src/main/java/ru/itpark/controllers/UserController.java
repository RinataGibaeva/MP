package ru.itpark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpark.dto.ProductDto;
import ru.itpark.dto.UserDto;
import ru.itpark.dto.UserRegistrationDto;
import ru.itpark.services.UsersService;

import java.util.List;

// Контроллер - объекты классов-контроллеров
// принимают http-запросы и соответствующе обрабатывают их
// Чтобы дать понять Spring-у, что у нас класс-контроллер
// мы ставим аннотацию @Controller
// но, если нам надо, чтобы контроллер принимал на вход
// JSON-объекты и возвращал JSON-объекты
// мы ставим аннотацию @RestController
@RestController
public class UserController {

    // классы сервисов обычно содержат логику приложений
    // таким образом, классы-контроллеры - это
    // всегда обертки над сервисами, то есть
    // методы контроллеров просто делегируют выполнение
    // задачи на сервис

    // для этого мы предусмотрели в контроллере
    // поле типа UsersService
    // Spring получив на вход аннотацию @Autowired
    // сам определит, какой сервис подходит в это поле
    @Autowired
    private UsersService service;

    // данная аннотация указывает, что этот
    // метод будет обрабатывать запрос
    // POST /users
    @PostMapping("/users")
    public ResponseEntity<UserDto>
    registration(@RequestBody UserRegistrationDto user) {
        // передаем задачу регистрации пользователя
        // соответствующему сервису
        // получаем результат регистрации и возвращаем
        // клиентскому приложению ответ
        UserDto resultUser = service.registration(user);
        return ResponseEntity
                .ok(resultUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password) {
        String token = service.login(login, password);
        return ResponseEntity
                .ok()
                .header("Auth-Token", token)
                .build();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto>
    product(@RequestBody ProductDto product,
            @RequestHeader("Auth-Token") String token) {
        return ResponseEntity
                .ok(service.newproduct(token, product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestHeader("Auth-Token") String token) {
        return ResponseEntity
                .ok(service.getAllProducts(token));
    }
}
