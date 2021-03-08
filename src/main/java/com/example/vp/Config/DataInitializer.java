package com.example.vp.Config;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeShop;
import com.example.vp.Model.BikeType;
import com.example.vp.Model.User;
import com.example.vp.Service.BikeService;
import com.example.vp.Service.BikeShopService;
import com.example.vp.Service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final UserService userService;
    private final BikeShopService bikeShopService;
    private final BikeService bikeService;

    public DataInitializer(UserService userService, BikeShopService bikeShopService, BikeService bikeService) {
        this.userService = userService;
        this.bikeShopService = bikeShopService;
        this.bikeService = bikeService;
    }

    @PostConstruct
    public void initData(){
        List<Bike> bikeList = new ArrayList<>();
        bikeList.add(bikeService.createBike("Bianchi", "Red", BikeType.Classic, 350));
        bikeList.add(bikeService.createBike("Cannondale", "Blue", BikeType.Mountain_Climbing, 400));
        bikeList.add(bikeService.createBike("Cube", "Black", BikeType.Road_Bike, 600));
        bikeList.add(bikeService.createBike("Ghost", "Grey", BikeType.Road_Bike, 500));
        this.bikeShopService.create("Shop 1", "Gernika", bikeList);
        bikeList.remove(2);
        this.bikeShopService.create("Shop 2", "Partizanska", bikeList);
        bikeList.add(bikeService.createBike("Giant", "Yellow", BikeType.Mountain_Climbing, 700));
        bikeList.add(bikeService.createBike("Max", "Blue", BikeType.Classic, 550));
        this.bikeShopService.create("Shop 3", "Ilindenska", bikeList);
        bikeList.remove(1);
        bikeList.remove(2);
        this.bikeShopService.create("Shop 4", "Crniche", bikeList);
        this.userService.userRegister("stefan", "stefan123", 2000);
        this.userService.userRegister("jovan", "jovan123", 2000);

    }

}
