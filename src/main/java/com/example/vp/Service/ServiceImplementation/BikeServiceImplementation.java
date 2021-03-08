package com.example.vp.Service.ServiceImplementation;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeType;
import com.example.vp.Model.Exceptions.InvalidBikeIdException;
import com.example.vp.Repository.BikeRepository;
import com.example.vp.Repository.BikeShopRepository;
import com.example.vp.Service.BikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImplementation implements BikeService {

    private final BikeRepository bikeRepository;

    public BikeServiceImplementation(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike getBikeById(long id) {
        return this.bikeRepository.findById(id).orElseThrow(InvalidBikeIdException::new);
    }

    @Override
    public List<Bike> listBikes() {
        return this.bikeRepository.findAll();
    }

    @Override
    public Bike createBike(String brand, String color, BikeType bikeType, int price) {
        Bike bike = new Bike(brand, color, bikeType, price);
        return this.bikeRepository.save(bike);
    }
}
