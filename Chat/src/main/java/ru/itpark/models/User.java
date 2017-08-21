package ru.itpark.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String login;
    private String hashPassword;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Token> tokens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Product> products;

    public User() {
    }


    public User(String name, String login, String hashPassword) {
        this.name = name;
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
