package com.abelkelly.DBServices;

import com.abelkelly.Config.Helpers;
import com.abelkelly.Token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogOutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    private final Helpers helpers;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("hello world");
        helpers.logout(request);
    }
}
