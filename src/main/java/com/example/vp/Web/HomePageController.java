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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomePageController {

    private final BikeShopService bikeShopService;
    private final BikeService bikeService;
    private final UserService userService;

    public HomePageController(BikeShopService bikeShopService, BikeService bikeService, UserService userService) {
        this.bikeShopService = bikeShopService;
        this.bikeService = bikeService;
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Model model, HttpServletRequest request){
        model.addAttribute("shops", bikeShopService.getAllBikeShops());
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userCash", user.getUserMoney());
        if(user.getBikesSize() > 0) {
            model.addAttribute("bikes", user.getBikeList());
        }else{
            List<Bike> bikeList = new ArrayList<>();
            model.addAttribute("bikes", bikeList);
        }
        return "homePage.html";
    }
    @PostMapping("/dropARent/{id}")
    public String dropARent(@PathVariable long id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String action = userService.dropARent(user, bikeService.getBikeById(id));
        return "redirect:/home";
    }
}
