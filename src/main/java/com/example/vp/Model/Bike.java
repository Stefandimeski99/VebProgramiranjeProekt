package com.example.vp.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bike {

    private String brand;
    private String color;
    @Enumerated(EnumType.STRING)
    private BikeType bikeType;
    private int price;

    @ManyToMany(mappedBy = "bikeList")
    List<BikeShop> bikeShopList;
    @Id
    @GeneratedValue
    private long bikeId;

    @ManyToOne
    @JoinColumn
    private User user;

    public Bike() {
    }

    public Bike(String brand, String color, BikeType bikeType, int price) {
        this.brand = brand;
        this.color = color;
        this.bikeType = bikeType;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }
}
