package com.example.vp.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class BikeShop {

    private int totalBikes;
    private int totalMoneyMade;
    private String shopName;
    private String shopLocation;

    @ManyToMany
    @JoinTable
    public List<Bike> bikeList;

    @Id
    @GeneratedValue
    private int shopId;

    public BikeShop() {
    }

    public BikeShop(String shopName, String shopLocation, int totalMoneyMade, int totalBikes, List<Bike> bikeList) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.totalMoneyMade = totalMoneyMade;
        this.totalBikes = totalBikes;
        this.bikeList = bikeList;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getTotalBikes() {
        return totalBikes;
    }

    public void setTotalBikes(int totalBikes) {
        this.totalBikes = totalBikes;
    }

    public int getTotalMoneyMade() {
        return totalMoneyMade;
    }

    public void setTotalMoneyMade(int totalMoneyMade) {
        this.totalMoneyMade = totalMoneyMade;
    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

}