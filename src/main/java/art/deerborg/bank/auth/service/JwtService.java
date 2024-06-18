package art.deerborg.bank.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("deer", "borg");
        return createToken(claims, email);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String email = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals (email) && expirationDate.after(new Date());
    }

    public String extractUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    private Date extractExpiration(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    private String createToken(Map<String, Object> claims,String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
