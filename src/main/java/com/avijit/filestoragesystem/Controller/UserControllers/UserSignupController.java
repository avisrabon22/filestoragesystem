package com.avijit.filestoragesystem.Controller.UserControllers;


import com.avijit.filestoragesystem.DTO.UserDto.UserSignupRequestDto;
import com.avijit.filestoragesystem.Service.UserSignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserSignupController {
    private final UserSignupService userSignupService;

    public UserSignupController(UserSignupService userSignupService) {
        this.userSignupService = userSignupService;

    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        if (userSignupRequestDto.getEmail() == null || userSignupRequestDto.getPassword() == null || userSignupRequestDto.getName() == null || userSignupRequestDto.getGender() == null|| userSignupRequestDto.getRole() == null)
            return ResponseEntity.badRequest().body("Please provide all the fields");

         try{
             userSignupService.createUser(userSignupRequestDto);
         }
         catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());
         }
        return ResponseEntity.ok("User created");
    }


}
