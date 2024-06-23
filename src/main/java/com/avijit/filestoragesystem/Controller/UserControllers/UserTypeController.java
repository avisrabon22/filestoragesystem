package com.avijit.filestoragesystem.Controller.UserControllers;

import com.avijit.filestoragesystem.DTO.UserDto.UserTypeRequestDto;
import com.avijit.filestoragesystem.DTO.UserDto.UserTypeResponsDto;
import com.avijit.filestoragesystem.Exception.UserTypeEmpty;
import com.avijit.filestoragesystem.Service.UserService.UserTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usertype")
public class UserTypeController {
    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }
//    ***********************************************************************************************
    @GetMapping("/getusertype")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserTypeResponsDto>> getUserType(){
        return ResponseEntity.ok(userTypeService.getUserType());
    }

//    ***********************************************************************************************
@PostMapping("/addusertype")
@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUsertType( @RequestBody UserTypeRequestDto userTypeRequestDto){
        try{
            userTypeService.addUserType(userTypeRequestDto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("User Type Added Successfully");
    }

    @GetMapping("/getusertypesignup")
    public ResponseEntity<?> getUserTypeSignup() throws UserTypeEmpty {
         List<UserTypeResponsDto> userTypeResponsDto;
        try{
            userTypeResponsDto=userTypeService.getUserTypeSignup();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userTypeResponsDto);
    }

}
