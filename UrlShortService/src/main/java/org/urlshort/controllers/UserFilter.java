package org.urlshort.controllers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.urlshort.utils.UserInfo;

import java.io.IOException;


@Component
@WebFilter("/urls")
public class UserFilter extends OncePerRequestFilter {

    @Autowired
    UserInfo userInfo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI().split("/");
        if (path.length == 2) {
            if (path[0].equals("sh")){
                var value = request.getHeader("USERID");
                Long userId = null;
                if (value != null){
                    userId = Long.parseLong(value);
                }
                userInfo.setUserId(null);
            }
        }
        filterChain.doFilter(request, response);
    }
}


