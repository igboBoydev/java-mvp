package com.abelkelly.ControllersRoutes;

import com.abelkelly.Repository.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthenticatedController {
    private AppUserRepository appUserRepository;

    @GetMapping("/get-profile")
    public Object getUser(Authentication authentication){
      //  String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = authentication.getPrincipal();
       return user;
    }
}
