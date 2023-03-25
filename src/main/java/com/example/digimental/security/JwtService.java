package com.example.digimental.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private String SECRET_KEY = "secret";

    //method to extract name from token
    public String extractUsername(String token) {
        return extractCliam(token, Claims::getSubject);

    }

    //method to get specific claims
    public <T> T extractCliam(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    ///method to extract all claims
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    ///generate token
    public String generateToken(String userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    ///validate token

    public boolean isTokenValid(String token,UserDetails userDetails){
        String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }
///check if token is expired
    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }
    //extract token expiration
    private Date extractTokenExpiration(String token) {
        return extractCliam(token,Claims::getExpiration);
    }
}
