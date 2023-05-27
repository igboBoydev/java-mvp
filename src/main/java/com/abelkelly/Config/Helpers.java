package com.abelkelly.Config;

import com.abelkelly.Models.AppUser;
import com.abelkelly.Roles.TokenType;
import com.abelkelly.Token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class Helpers {
    private final TokenRepository tokenRepository;

    public Helpers(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveUserToken(AppUser user, String token) {
        var Token = com.abelkelly.Token.Token.builder()
                .users(user)
                .key(token)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(Token);
    }

    public void revokeUsersTokens(AppUser user){
        var validUserTokens = tokenRepository.findAllValidTokensByUsers(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public boolean checkTokenValidity(String token){
        return tokenRepository.findByKey(token).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
    }

    public void logout(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByKey(jwt).orElse(null);
        if(storedToken != null){
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
