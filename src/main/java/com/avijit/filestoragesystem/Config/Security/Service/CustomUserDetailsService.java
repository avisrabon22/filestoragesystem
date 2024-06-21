package com.avijit.filestoragesystem.Config.Security.Service;

import com.avijit.filestoragesystem.Model.UserModel;
import com.avijit.filestoragesystem.Repo.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user =userRepository.findByEmail(username);
        if(user.isPresent())
        {
            return User.builder()
                    .username(user.get().getEmail())
                    .password(user.get().getPassword())
                    .roles(user.get().getUserType().getType())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
