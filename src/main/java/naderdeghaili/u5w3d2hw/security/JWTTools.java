package naderdeghaili.u5w3d2hw.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String key;

    public String createToken(Dipendente dipendente) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();

    }

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("effettua il login");
        }
    }

    public UUID getIdFromToken(String token) {

        return UUID.fromString(Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());

    }
}
