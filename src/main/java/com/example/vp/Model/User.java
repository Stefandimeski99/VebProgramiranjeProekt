package com.example.vp.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;
    private String password;
    private int userMoney;

    @OneToMany(mappedBy = "user")
    private List<Bike> userBikes;

    private int bikesSize;

    public User() {
    }

    public User(String username, String password, int userMoney) {
        this.username = username;
        this.password = password;
        this.userMoney = userMoney;
        this.bikesSize = 0;
    }

    public int getBikesSize() {
        return bikesSize;
    }

    public void increaseBikesSize() {
        this.bikesSize = this.bikesSize + 1;
    }

    public void decreaseBikesSize() {
        this.bikesSize = this.bikesSize - 1;
    }

    public List<Bike> getBikeList() {
        return userBikes;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.userBikes = bikeList;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
