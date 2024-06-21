package com.avijit.filestoragesystem.Config.Security.JwtConfig;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${spring.app.jwtSecret}")
    private  String SECRET;
    @Value("${spring.app.jwtExpirationMs}")
    private long EXPIRATION_TIME;
    @Value("${spring.app.jwtHeader}")
    private String HEADER_STRING;


    //   **************************************************************************************
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    //   **************************************************************************************
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = null;

        if(request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(HEADER_STRING)) {
                    bearerToken= cookie.getValue();
                }
            }
        }
        if (bearerToken != null && bearerToken.startsWith("Bearer+")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //   ***************************************************************************************
    public String generateJwtToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return Jwts.builder().claim("roles", userDetails.getAuthorities())
                .subject(username)
                .signWith(key())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    //    ****************************************************************************************
    public String getUserNameFromJwtToken(String Token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key()).build()
                .parseSignedClaims(Token)
                .getPayload()
                .getSubject();
    }

    //    ****************************************************************************************
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}