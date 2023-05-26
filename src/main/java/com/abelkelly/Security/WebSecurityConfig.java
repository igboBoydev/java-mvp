package com.abelkelly.Security;

import com.abelkelly.Config.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    public static final String[] AUTHENTICATED_URLS = { "/api/v1/registration/**", "/api/v1/login/**" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.
                cors().
                and().
                csrf().
                disable().
                authorizeHttpRequests().
                requestMatchers(AUTHENTICATED_URLS).
                permitAll().
                anyRequest().
                authenticated().
                and().
                exceptionHandling().
                authenticationEntryPoint((request, response, authException) -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    ObjectMapper mapper = new ObjectMapper();
                    response.setStatus(401);
                    responseMap.put("error", true);
                    responseMap.put("message", "Unauthorized");
                    response.setHeader("content-type", "application/json");
                    String responseMsg = mapper.writeValueAsString(responseMap);
                    response.getWriter().write(responseMsg);
                }).
                and().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authenticationProvider(authenticationProvider).
                addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout().logoutUrl("/api/v1/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler(((request, response, authentication) -> {
                    SecurityContextHolder.clearContext();
                }));

        return httpSecurity.build();
    }
}
