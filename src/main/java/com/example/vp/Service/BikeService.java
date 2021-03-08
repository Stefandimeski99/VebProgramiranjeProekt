package com.example.vp.Service;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeType;

import java.util.List;

public interface BikeService {

    Bike getBikeById(long id);
    List<Bike> listBikes();
    Bike createBike(String brand, String color, BikeType bikeType, int price);

}
