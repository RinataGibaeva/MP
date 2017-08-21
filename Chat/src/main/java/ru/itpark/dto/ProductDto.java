package ru.itpark.dto;

public class ProductDto {
    private int id;
    private String product_name;
    private Float price;
    private String photoUrl;

    public ProductDto() {
    }

    public ProductDto(String product_name, Float price, String photoUrl) {
        this.product_name = product_name;
        this.price = price;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
