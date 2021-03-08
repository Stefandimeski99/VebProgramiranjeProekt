package com.example.vp.Service.ServiceImplementation;

import com.example.vp.Model.Bike;
import com.example.vp.Model.BikeType;
import com.example.vp.Model.Exceptions.InvalidUserCredentialsException;
import com.example.vp.Model.User;
import com.example.vp.Repository.BikeRepository;
import com.example.vp.Repository.UserRepository;
import com.example.vp.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final BikeRepository bikeRepository;

    public UserServiceImplementation(UserRepository userRepository, BikeRepository bikeRepository) {
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
    }

    @Override
    public User userLogin(String username, String password) {
        List<User> users = userRepository.findAll();
        User user = users.stream().filter(r->r.getUsername().equals(username)).findFirst().get();
        if(user == null || !user.getPassword().equals(password)){
            throw new InvalidUserCredentialsException();
        }
        return user;
    }

    @Override
    public void userRegister(String username, String password, int money) {
        this.userRepository.save(new User(username, password, money));
    }

    @Override
    public String rentABike(User user, Bike bike) {
        if(user.getUserMoney() < bike.getPrice()){
            return "Unsufficient money";
        }
        if(user.getBikesSize() == 0) {
            List<Bike> bikeList = new ArrayList<>();
            bikeList.add(bike);
            user.setBikeList(bikeList);
            int userMoney = user.getUserMoney();
            user.setUserMoney(userMoney - bike.getPrice());
            user.increaseBikesSize();
            return "Bike successfully added to rent";
        }
        long bikeId = bike.getBikeId();
        if(!user.getBikeList().stream().filter(r->r.getBikeId() == bikeId).findFirst().isPresent()) {
            List<Bike> bikeList = user.getBikeList();
            bikeList.add(bike);
            user.setBikeList(bikeList);
            int userMoney = user.getUserMoney();
            user.setUserMoney(userMoney - bike.getPrice());
            user.increaseBikesSize();
            return "Bike successfully added to rent";
        }
        return "You already have this bike for renting";
    }

    @Override
    public String dropARent(User user, Bike bike){
        user.setUserMoney(user.getUserMoney() + bike.getPrice());
        List<Bike> bikeList = user.getBikeList();
        user.decreaseBikesSize();
        List<Bike> bikeList2 = bikeList.stream().filter(r->r.getBikeId() != bike.getBikeId()).collect(Collectors.toList());
        user.setBikeList(bikeList2);
        return "Bike successfully dropped from rent";
    }
}
