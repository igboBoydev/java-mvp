package com.abelkelly.Config;

import com.abelkelly.Exceptions.ApiRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

             final String authHeader = request.getHeader("Authorization");
             final String jwt;
             final String userEmail;
             if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                 filterChain.doFilter(request, response);
                 return;
             }
             jwt = authHeader.substring(7);
             // extract user email from jwt token;
             userEmail = jwtService.extractUsername(jwt);
             if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                 // get user details from database
                 UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                 // check if user and token is valid
                 if (jwtService.isTokenValid(jwt, userDetails)) {
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     // update security context holder
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 } else {
                     throw new ApiRequestException("Token invalid or expired");
                 }
             }
             filterChain.doFilter(request, response);
    }
}
