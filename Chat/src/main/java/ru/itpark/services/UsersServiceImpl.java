package ru.itpark.services;

import ru.itpark.dto.ProductDto;
import ru.itpark.dto.UserDto;
import ru.itpark.dto.UserRegistrationDto;

import ru.itpark.models.Product;
import ru.itpark.models.Token;
import ru.itpark.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.repository.ProductsRepository;
import ru.itpark.repository.TokensRepository;
import ru.itpark.repository.UsersRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// аннотация Service синоним аннотации Component
// она позволяет спрингу поместить экзепляр класса
// UsersServiceImpl в поле класса-контроллера
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private TokensRepository tokensRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private SecureRandom random = new SecureRandom();

    @Override
    public UserDto registration(UserRegistrationDto user) {
        // 1. Хешировать пароль
        String hashPassword = encoder.encode(user.getPassword());
        // 2. Сохранить в БД
        User model = new User(user.getName(), user.getLogin(), hashPassword);
        usersRepository.save(model);
        // 3. Получить id и имя и вернуть как результат
        return new UserDto(model.getId(), model.getName());
    }


    @Override
    public String login(String login, String password) {
        // найти пользователя по логину
        User user = usersRepository.findFirstByLogin(login);
        if (user != null &&
                encoder.matches(password, user.getHashPassword())) {
            // генерируем случайную строку - токен
            String token = new BigInteger(130, random).toString(32);
            // добавили пользователю данный токен
            Token tokenModel = new Token(token, LocalDateTime.now(), user);
            // добавили всю новую информацию в бд
            tokensRepository.save(tokenModel);
            // вернули токен
            return token;
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public ProductDto newproduct(String token, ProductDto product) {
        User user = usersRepository.findOneByToken(token);
        Product model = new Product(product.getProduct_name(), product.getPrice(), user, LocalDateTime.now(), product.getPhotoUrl());
        productsRepository.save(model);
        return product;
    }

    @Override
    public List<ProductDto> getAllProducts(String token) {
        User user = usersRepository.findOneByToken(token);
        List<Product> products = productsRepository.findAllByUser(user);
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            result.add(new ProductDto(product.getProductName(), product.getPrice(),
                    product.getPhotoUrl()));
        }
        return result;
    }
}
