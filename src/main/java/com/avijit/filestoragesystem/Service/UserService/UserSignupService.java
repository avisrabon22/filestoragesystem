package com.avijit.filestoragesystem.Service.UserService;

import com.avijit.filestoragesystem.DTO.UserDto.UserSignupRequestDto;
import com.avijit.filestoragesystem.Exception.UserAlreadyExist;
import com.avijit.filestoragesystem.Exception.UserTypeEmpty;
import com.avijit.filestoragesystem.Model.UserModel;
import com.avijit.filestoragesystem.Model.UserType;
import com.avijit.filestoragesystem.Repo.UserRepository;
import com.avijit.filestoragesystem.Repo.UserTypeRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTypeRepo userTypeRepo;


    public UserSignupService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserTypeRepo userTypeRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepo = userTypeRepo;
    }

    public void createUser(UserSignupRequestDto userSignupRequestDto) throws UserAlreadyExist, UserTypeEmpty {
        // Create user
        Optional<UserModel> user = userRepository.findByEmail(userSignupRequestDto.getEmail());
        Optional<UserType> userType = userTypeRepo.findByType(userSignupRequestDto.getRole());
        if (user.isPresent())
            throw new UserAlreadyExist("User already exist");

       if (userType.isEmpty())
           throw  new UserTypeEmpty("User type not found");
        if (userType.get().equals("ADMIN")) {
            throw new UserTypeEmpty("Wrong user type");
        }

        UserModel userModel = new UserModel();
        userModel.setName(userSignupRequestDto.getName());
        userModel.setEmail(userSignupRequestDto.getEmail());
        String encodedPassword=passwordEncoder.encode(userSignupRequestDto.getPassword());
        userModel.setPassword(encodedPassword);
        userModel.setGender(userSignupRequestDto.getGender());
        userModel.setUserType(userType.get());
        userRepository.save(userModel);

    }
}
