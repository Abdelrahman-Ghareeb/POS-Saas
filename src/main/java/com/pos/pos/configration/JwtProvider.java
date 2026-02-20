package com.pos.pos.configration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {


    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities= authentication.getAuthorities();
        String roles = papulateRoles(authorities);

        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+JwtConstant.EXPIRE_TIME))
                .claim("email",authentication.getName())
                .claim("authorities",roles)
                .signWith(secretKey).compact();
    }


    public String getEmailFromToken(String token) {
        token= token.substring(7);
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get("email",String.class);
    }

    private String papulateRoles(Collection<? extends GrantedAuthority> authorities) {

    Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auth.add(authority.getAuthority());
        }
        return String.join(",",auth);
    }
}
