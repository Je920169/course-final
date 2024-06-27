package com.example.demo.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Exclude the login and auth URLs from filtering
        String uri = request.getRequestURI();
        if (uri.startsWith("/auth/login") || uri.startsWith("/auth/logout") || uri.startsWith("/auth") || uri.startsWith("/css") || uri.startsWith("/js")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check if the user is logged in
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            // If not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/auth/login");
        } else {
            // If logged in, proceed with the request
            filterChain.doFilter(request, response);
        }
    }
}
