package com.example.vp.Service.ServiceImplementation;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeShop;
import com.example.vp.Repository.BikeRepository;
import com.example.vp.Repository.BikeShopRepository;
import com.example.vp.Repository.UserRepository;
import com.example.vp.Service.BikeShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeShopServiceImplementation implements BikeShopService {

    private final BikeShopRepository bikeShopRepository;
    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;

    public BikeShopServiceImplementation(BikeShopRepository bikeShopRepository, BikeRepository bikeRepository, UserRepository userRepository) {
        this.bikeShopRepository = bikeShopRepository;
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BikeShop create(String shopName, String shopLocation, List<Bike> bikeList) {
        int totalBikes = bikeList.size();
        int totalMoneyMade = bikeList.stream().mapToInt(Bike::getPrice).sum();
        BikeShop bikeShop = new BikeShop(shopName, shopLocation, totalMoneyMade, totalBikes, bikeList);
        return this.bikeShopRepository.save(bikeShop);
    }

    @Override
    public List<BikeShop> getAllBikeShops(){
        return this.bikeShopRepository.findAll();
    }

    @Override
    public Bike addBikeToRent(Bike bike, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        bikeShop.getBikeList().add(bike);
        return bike;
    }

    @Override
    public Bike rentABike(Bike bike, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        Bike bike1 = (Bike) bikeShop.getBikeList().stream().filter(r->r.getBikeId() == bike.getBikeId());
        bikeShop.getBikeList().remove(bike1);
        bikeShop.setTotalMoneyMade(bikeShop.getTotalMoneyMade() - bike1.getPrice());
        return bike1;
    }

    @Override
    public Bike returnFromRent(Bike bike, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        bikeShop.getBikeList().add(bike);
        return bike;
    }

    @Override
    public List<Bike> getBikesWithPriceLowerThan(int price, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        return (List<Bike>) bikeShop.getBikeList().stream().filter(r->r.getPrice() < price);
    }

    @Override
    public List<Bike> getBikesWithPriceHigherThan(int price, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        return (List<Bike>) bikeShop.getBikeList().stream().filter(r->r.getPrice() > price);
    }

    @Override
    public List<Bike> getBikesByType(String bikeType, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        return (List<Bike>) bikeShop.getBikeList().stream().filter(r->r.getBikeType().equals(bikeType));
    }

    @Override
    public List<Bike> getBikesByBrand(String brand, int id) {
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        return (List<Bike>) bikeShop.getBikeList().stream().filter(r->r.getBrand().equals(brand));
    }

    @Override
    public List<Bike> getBikesFromShopId(int id){
        BikeShop bikeShop = bikeShopRepository.findById(id).get();
        return bikeShop.getBikeList();
    }

    @Override
    public BikeShop getBikeShopById(int id){
        return this.bikeShopRepository.findById(id).get();
    }
}
