package edu.escuelaing.synchealthback.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private String timeExpiration;

    // Generate JWT token
    public String generateToken(String username) {
        return builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSign(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSign()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            log.error("Invalid token: {}", e.getMessage());
            return false;
        }
    }


    /**
     * Get claim from token
     * @param token: the authenticate token
     * @return claim
     */
    public String getClaim(String token, Function<Claims, String> resolver) {
        Claims claims = getAllClaimsFromToken(token);
        return resolver.apply(claims);
    }

    /**
     * Get username from token
     * @param token: the authenticate token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Get all claims from token
     * @param token: the authenticate token
     * @return Claims
     */
    public Claims getAllClaimsFromToken(String token) {
        return parser().setSigningKey(getSign()).build().parseClaimsJws(token).getBody();
    }

    // Get tocken's sign
    public Key getSign() {
        byte[] secretBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
