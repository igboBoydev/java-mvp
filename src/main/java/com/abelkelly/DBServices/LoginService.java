package com.abelkelly.DBServices;

import com.abelkelly.Config.JwtService;
import com.abelkelly.Repository.AppUserRepository;
import com.abelkelly.RequestSchema.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
       var user = appUserRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return token;
    }

}
