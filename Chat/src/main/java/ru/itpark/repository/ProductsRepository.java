package ru.itpark.repository;

import org.springframework.data.jpa.repository.Query;
import ru.itpark.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.User;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
    //Product findFirstByProductName(String productName);
    List<Product> findAllByUser(User user);

}
