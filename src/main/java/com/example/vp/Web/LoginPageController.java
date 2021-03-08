package com.example.vp.Web;

import com.example.vp.Model.Exceptions.InvalidUserCredentialsException;
import com.example.vp.Model.User;
import com.example.vp.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    private final UserService userService;

    public LoginPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginPage(Model model){
        return "loginPage.html";
    }

    @PostMapping("/userLogin")
    public String userLogin(HttpServletRequest request, Model model){
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = userService.userLogin(username, password);
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception){
            return "redirect:/login";
        }
    }
}
