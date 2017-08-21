package ru.itpark.services;

import ru.itpark.dto.ProductDto;
import ru.itpark.dto.UserDto;
import ru.itpark.dto.UserRegistrationDto;

import java.util.List;

public interface UsersService {
    UserDto registration(UserRegistrationDto user);

    String login(String login, String password);

    ProductDto newproduct(String token, ProductDto product);

    List<ProductDto> getAllProducts(String token);
}
