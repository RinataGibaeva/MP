package ru.itpark.repository;

import org.springframework.data.jpa.repository.Query;
import ru.itpark.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    User findFirstByLogin(String login);

    @Query("from User user, Token token where token.token = ?1 and token.user.id = user.id")
    User findOneByToken(String token);
}
