package com.abelkelly.ControllersRoutes;

import com.abelkelly.Models.AppUser;
import com.abelkelly.Repository.AppUserRepository;
import com.abelkelly.ResponseSchema.AppUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthenticatedController {
    private AppUserRepository appUserRepository;

    @GetMapping("/get-profile")
    public AppUserResponse getUser(){
       String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> user = appUserRepository.findByEmail(userEmail);
       return new AppUserResponse(
               user.get().getFirstName(),
               user.get().getLastName(),
               user.get().getEmail(),
               user.get().getAppUserRole(),
               user.get().getLocked(),
               user.get().getLocked()
       );
    }
}
