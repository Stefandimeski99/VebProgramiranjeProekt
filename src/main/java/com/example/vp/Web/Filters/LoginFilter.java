package com.example.vp.Web.Filters;

import com.example.vp.Model.User;
import org.springframework.context.annotation.Profile;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class LoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        User user = (User)request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if (!"/login".equals(path) && user == null && !"/login/userLogin".equals(path) && !"/img/no.jpg".equals(path) && !"/h2".equals(path)
            && !"/register".equals(path) && !"/register/registerUser".equals(path)&&!"/img/loginPageBg.jpg".equals(path)) {
            response.sendRedirect("/login");
        } else {
            super.doFilter(request, response, chain);
        }
    }

    @Override
    public void destroy() {

    }
}
