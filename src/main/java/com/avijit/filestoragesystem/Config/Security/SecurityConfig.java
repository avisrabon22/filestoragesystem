package com.avijit.filestoragesystem.Config.Security;

import com.avijit.filestoragesystem.Config.Security.JwtConfig.AuthEntryPoint;
import com.avijit.filestoragesystem.Config.Security.JwtConfig.AuthTokenFilter;
import com.avijit.filestoragesystem.Config.Security.JwtConfig.JwtUtils;
import com.avijit.filestoragesystem.Config.Security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;
    @Autowired
   private AuthEntryPoint unauthorizedHandler;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/api/v1/signup").permitAll()
                        .requestMatchers("/api/v1/login").permitAll()
                        .requestMatchers("/api/v1/usertype/getusertypesignup").permitAll()
                        .requestMatchers("/api/v1/upload_file","/api/v1/download_file").hasRole("USER")
                        .anyRequest().authenticated());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler));
        http.csrf(csrf->csrf.disable());
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("avijit")
//            .password(passwordEncoder().encode("Avijit@123"))
//            .roles("USER")
//            .build();
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder().encode("Admin@123"))
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }


}