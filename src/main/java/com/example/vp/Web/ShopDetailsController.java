package com.example.vp.Web;

import com.example.vp.Model.Bike;
import com.example.vp.Model.User;
import com.example.vp.Service.BikeService;
import com.example.vp.Service.BikeShopService;
import com.example.vp.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop")
public class ShopDetailsController {

    private final BikeService bikeService;
    private final BikeShopService bikeShopService;
    private final UserService userService;

    public ShopDetailsController(BikeService bikeService, BikeShopService bikeShopService, UserService userService) {
        this.bikeService = bikeService;
        this.bikeShopService = bikeShopService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String shop(@PathVariable int id, Model model){
        model.addAttribute("bikesList", bikeShopService.getBikesFromShopId(id));
        model.addAttribute("bikeShopName", bikeShopService.getBikeShopById(id).getShopName());
        model.addAttribute("shopMoney", bikeShopService.getBikeShopById(id).getTotalMoneyMade());
        return "shopDetails.html";
    }

    @PostMapping("/rentABike/{id}")
    public String rentBike(@PathVariable Long id, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Bike bike = bikeService.getBikeById(id);
        String bikeRentMessage = userService.rentABike(user, bike);
        return "redirect:/home";
    }
}
