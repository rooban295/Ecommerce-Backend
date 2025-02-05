package com.ecom.E.commerce_backend.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;


@Service
public class JwtProvider {


    private SecretKey Key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){

        Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();

        String roles =populateAuthorites(authorities);

        String jwt=
                Jwts.builder()
                        .setIssuedAt(new Date())
                        .setExpiration((new Date(new Date().getTime()+8640000)))
                        .claim("email",auth.getName())
                        .claim("authorities",roles)
                        .signWith(Key)
                        .compact();
        return jwt;

    }


    public String getEmailJwtToken(String jwt){

        jwt =jwt.substring(7);

        Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(Key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

        String email=String.valueOf(claims.get("email"));  //String.valueOf() method which is used to convert any datatype value into String

        String authorities=String.valueOf((claims.get("authorities")));

        return email;

    }

    private String populateAuthorites(Collection<? extends GrantedAuthority> authorites) {

        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority:authorites){
            auths.add(authority.getAuthority());
        }

        return String.join(",",auths); //return "user,admin"
    }



}
