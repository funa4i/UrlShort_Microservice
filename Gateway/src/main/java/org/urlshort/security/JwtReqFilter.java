package org.urlshort.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.urlshort.exceptions.InvalidRoleForePath;
import org.urlshort.feign.clients.AccessControlApi;
import org.urlshort.service.JwtServ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtReqFilter extends OncePerRequestFilter {
    private final JwtServ jwtServ;
    private final AccessControlApi accessControlApi;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHead = request.getHeader("Authorization");
        String userId = null;
        String jwt = null;

        if (authHead != null && authHead.startsWith("Bearer ")){
            jwt = authHead.substring(7);
                userId = jwtServ.getUserId(jwt);
        }
        if(userId != null) {
            if (!accessControlApi
                    .accessCheck(request.getMethod() + ":" + request.getRequestURI(), Long.parseLong(userId))
                    .getAccess()) {
                throw new InvalidRoleForePath("Bad role");
            }

        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    new ArrayList<>()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
