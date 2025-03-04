package org.urlshort.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@WebFilter("/urls")
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI().split("/");
        MutableHttpServletRequest mutableHttpServletRequest = new MutableHttpServletRequest(request);
        if (path.length == 2) {
            if (path[0].equals("sh")){
                var userId = SecurityContextHolder.getContext().getAuthentication().getName();
                if (userId == null){
                    filterChain.doFilter(mutableHttpServletRequest, response);
                    return;
                }
                mutableHttpServletRequest.addHeader("USERID", userId);
            }
        }
        filterChain.doFilter(mutableHttpServletRequest, response);
    }
}
