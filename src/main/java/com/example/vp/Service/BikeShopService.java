package com.example.vp.Service;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeShop;

import java.util.List;

public interface BikeShopService {

    List<BikeShop> getAllBikeShops();
    BikeShop create(String shopName, String shopLocation, List<Bike> bikeList);
    Bike addBikeToRent(Bike bike, int id);
    Bike rentABike(Bike bike, int id);
    Bike returnFromRent(Bike bike, int id);
    List<Bike> getBikesWithPriceLowerThan(int price, int id);
    List<Bike> getBikesWithPriceHigherThan(int price, int id);
    List<Bike> getBikesByType(String bikeType, int id);
    List<Bike> getBikesByBrand(String brand, int id);
    List<Bike> getBikesFromShopId(int id);
    BikeShop getBikeShopById(int id);
}
