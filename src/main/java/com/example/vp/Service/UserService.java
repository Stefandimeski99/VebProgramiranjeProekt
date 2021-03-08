package com.example.vp.Service;

import com.example.vp.Model.Bike;
import com.example.vp.Model.User;

public interface UserService {

    public User userLogin(String username, String password);
    public void userRegister(String username, String password, int money);
    public String rentABike(User user, Bike bike);
    public String dropARent(User user, Bike bike);
}
