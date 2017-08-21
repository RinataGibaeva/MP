package ru.itpark.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String product_name;
    private Float price;
    private LocalDateTime creationTime;

    private String photoUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Product() {
    }

    public Product(String product_name, Float price, User user, LocalDateTime time, String photoUrl) {
        this.product_name = product_name;
        this.price = price;
        this.user = user;
        this.creationTime = time;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {return product_name; }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public Float getPrice() {return price; }

    public void setPrice(Float price) {this.price = price; }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

