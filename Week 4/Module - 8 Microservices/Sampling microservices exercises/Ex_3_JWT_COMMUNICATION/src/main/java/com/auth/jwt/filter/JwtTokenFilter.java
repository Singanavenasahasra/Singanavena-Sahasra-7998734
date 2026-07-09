package com.auth.jwt.filter;

import com.auth.jwt.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter { 
    @Autowired 
    private JwtTokenProvider jwtTokenProvider; 
    
    @Override 
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, 
            @NonNull HttpServletResponse response, 
            @NonNull FilterChain filterChain) 
            throws ServletException, IOException { 
            
        String token = resolveToken(request); 
        
        if (token != null && jwtTokenProvider.validateToken(token)) { 
            Authentication auth = jwtTokenProvider.getAuthentication(token); 
            SecurityContextHolder.getContext().setAuthentication(auth); 
        } 
        filterChain.doFilter(request, response); 
    } 
    
    private String resolveToken(HttpServletRequest request) { 
        String bearerToken = request.getHeader("Authorization"); 
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) { 
            return bearerToken.substring(7); 
        } 
        return null; 
    } 
}
