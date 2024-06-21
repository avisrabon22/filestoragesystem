package com.avijit.filestoragesystem.DTO.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private Character gender;
    private String role;
}
