package com.avijit.filestoragesystem.Config.Security.JwtConfig;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthTokenFilter  extends OncePerRequestFilter{
    @Autowired
    private  JwtUtils jwtUtils;
    @Autowired
    private  UserDetailsService userDetailsService;

    Logger logger = Logger.getLogger(AuthTokenFilter.class.getName());

//    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }

//    ****************************************************************************************
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e){
            logger.warning("Cannot set user authentication: " + e);
        }
        filterChain.doFilter(request, response);
    }

//    ****************************************************************************************
    private String parseJwt(HttpServletRequest request){
        return jwtUtils.getJwtFromHeader(request);
    }
}

