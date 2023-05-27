package com.abelkelly.DBServices;

import com.abelkelly.Config.Helpers;
import com.abelkelly.Config.JwtService;
import com.abelkelly.Repository.AppUserRepository;
import com.abelkelly.RequestSchema.LoginRequest;
import com.abelkelly.ResponseSchema.LoginResponse;
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
    private final Helpers helpers;

    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
       var user = appUserRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       helpers.revokeUsersTokens(user);

        String token = jwtService.generateToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user);
        helpers.saveUserToken(user, token);
        return new LoginResponse(token);
    }


}
