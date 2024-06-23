package com.avijit.filestoragesystem.Service.UserService;


import com.avijit.filestoragesystem.DTO.UserDto.UserTypeRequestDto;
import com.avijit.filestoragesystem.DTO.UserDto.UserTypeResponsDto;
import com.avijit.filestoragesystem.Exception.UserTypeEmpty;
import com.avijit.filestoragesystem.Model.UserType;
import com.avijit.filestoragesystem.Repo.UserTypeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserTypeService {
    private final UserTypeRepo userTypeRepo;

    public UserTypeService(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

//    ************Get User Type************
    public List<UserTypeResponsDto> getUserType(){
            List<String> userTypes =userTypeRepo.findAllType();
            if(userTypes.isEmpty())
                return null;

        List<UserTypeResponsDto> userTypeResponsDtos = new ArrayList<>();
for(String userType: userTypes){
    UserTypeResponsDto userTypeResponsDto = new UserTypeResponsDto();
    userTypeResponsDto.setRole(userType);
    userTypeResponsDtos.add(userTypeResponsDto);
}
        return userTypeResponsDtos;
        }

//        ************Add User Type************
    public void addUserType(UserTypeRequestDto userTypeRequestDto){
            UserType userType = new UserType();
            try {
                userType.setType(userTypeRequestDto.getRole());
                userTypeRepo.save(userType);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

//       ************Get User Type for Signup************
    public  List<UserTypeResponsDto> getUserTypeSignup() throws UserTypeEmpty {
            List<UserType> userTypesList=userTypeRepo.findAll();
            if(userTypesList.isEmpty())
                throw  new UserTypeEmpty("No User Type Found");

            List<UserTypeResponsDto> userTypeResponsDtos = new ArrayList<>();

            for(UserType userType: userTypesList){
                UserTypeResponsDto userTypeResponsDto = new UserTypeResponsDto();
                if (!userType.getType().equalsIgnoreCase("ADMIN"))
                {
                    userTypeResponsDto.setId(userType.getId());
                    userTypeResponsDto.setRole(userType.getType());
                    userTypeResponsDtos.add(userTypeResponsDto);
                }
            }

        return userTypeResponsDtos;
    }

}

