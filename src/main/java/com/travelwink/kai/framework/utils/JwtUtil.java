package com.travelwink.kai.framework.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.context.SecurityContext;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class JwtUtil {

    public static String generateToken(String subject, int ttlSeconds) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        log.info("Secret key format is {}", key.getFormat());
        log.info("Secret key encode is {}", key.getEncoded());
        log.info("Secret key algorithm is {}", key.getAlgorithm());

//        byte[] jwtSecretKey = DatatypeConverter.parseBase64Binary("!123");
//        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey);

        String jws = Jwts.builder()
                .header()
                .keyId(UUID.randomUUID().toString())
                .and()
                .issuer("Kai Api")
                .issuedAt(new Date())
                .subject(subject) // JSON Claims, or any byte[] content, with media type
                .audience().add("Kai Web").and()
                .expiration(DateUtils.addSeconds(new Date(), ttlSeconds))
                .notBefore(new Date())
                .signWith(key) // (4) if signing, or if encrypting
                .compact();
        log.info("Jws is {}", jws);
        return jws;
    }

}
