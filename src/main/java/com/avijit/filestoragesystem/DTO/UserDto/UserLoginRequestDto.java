package com.avijit.filestoragesystem.DTO.UserDto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    private String email;
    private String password;
}
