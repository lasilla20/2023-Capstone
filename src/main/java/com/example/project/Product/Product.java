package com.example.project.Product;

public class Product {

    private Long id;
    private String name;
    private String image;
    private int price;
    private Market market;

    public Product(Long id, String name, String image, int price, Market market) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.market = market;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
