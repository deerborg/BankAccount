package art.deerborg.bank.auth.config.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUnit implements Serializable {

    private static final long tokenValidityInSeconds = 30 * 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return null;
    }
    public Date getExpirationDateFromToken(String token) {
        return null;
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return null;
    }

    private Claims getAllClaimsFromToken(String token) {
        return null;
    }

    private Boolean isTokenExpired(String token) {
        return null;
    }
    public String generateToken(UserDetails userDetails) {
        return null;
    }
    private String doGenerateToken(String subject, UserDetails userDetails) {
        return null;
    }

}
