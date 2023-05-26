package com.abelkelly.ControllersRoutes;

import com.abelkelly.Config.JwtService;
import com.abelkelly.DBServices.LoginService;
import com.abelkelly.Repository.AppUserRepository;
import com.abelkelly.RequestSchema.LoginRequest;
import com.abelkelly.ResponseSchema.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
  //  private final TokenRepository tokenRepository;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        String token = loginService.login(request);
        return new  LoginResponse(token);
    }

//    private void revokeAllUserTokens(AppUser users) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUsers(users.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
}
