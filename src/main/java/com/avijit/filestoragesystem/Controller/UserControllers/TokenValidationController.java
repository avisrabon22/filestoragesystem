package com.avijit.filestoragesystem.Controller.UserControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TokenValidationController {
    @GetMapping("/ValidateToken")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String validateToken(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return "Welcome back "+username;
    }
}
