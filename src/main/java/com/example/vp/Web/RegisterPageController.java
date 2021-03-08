package com.example.vp.Web;

import com.example.vp.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterPageController {

    private final UserService userService;

    public RegisterPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registerPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "signUp.html";
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               @RequestParam int money,
                               Model model){
        if(!password.equals(confirmPassword)){
            return "redirect:/register?error=Your password and confirm password do not match";
        }
        if(username.length() < 4){
            return "redirect:/register?error=Your username length has to be longer than 4";
        }
        if(password.length() < 6){
            return "redirect:/register?error=Your password length has to be longer than 6";
        }
        userService.userRegister(username, password, money);
        return "redirect:/login";
    }
}
