package com.abelkelly.ControllersRoutes;

import com.abelkelly.DBServices.RegistrationService;
import com.abelkelly.RequestSchema.RegistrationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
