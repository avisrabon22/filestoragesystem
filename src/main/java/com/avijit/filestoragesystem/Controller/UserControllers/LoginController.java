package com.avijit.filestoragesystem.Controller.UserControllers;

import com.avijit.filestoragesystem.Config.Security.JwtConfig.JwtUtils;
import com.avijit.filestoragesystem.DTO.UserDto.LoginRequestDto;
import com.avijit.filestoragesystem.DTO.UserDto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final AuthenticationManager authenticationManager;
private final JwtUtils jwtUtils;
    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        Authentication authentication;

        try{
              authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        }
        catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("message", "Invalid Username or Password");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails
                            .getAuthorities().stream()
                            .map(item -> item.getAuthority())
                            .toList();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUsername(userDetails.getUsername());
        ResponseCookie cookie = ResponseCookie.from("Authorization", "Bearer+"+jwtToken)
                .httpOnly(true)
                .maxAge(3600)
                .path("/")
                .secure(true)
                .build();
response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().body(loginResponseDto.getUsername()+" has logged in successfully");
    }
}
