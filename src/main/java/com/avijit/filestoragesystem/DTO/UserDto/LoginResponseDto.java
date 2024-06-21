package com.avijit.filestoragesystem.DTO.UserDto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDto {
    private  String token;
    private String username;
    private List<String> roles;
}
