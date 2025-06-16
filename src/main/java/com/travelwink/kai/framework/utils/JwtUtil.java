package com.travelwink.kai.framework.utils;

import com.travelwink.kai.framework.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(String subject) {
        // SecretKey secret = Jwts.SIG.HS256.key().build();
        String keyString = jwtProperties.getSecretKey();
        SecretKey secret = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
        log.debug("Secret key format is {}", secret.getFormat());
        log.debug("Secret key encode is {}", secret.getEncoded());
        log.debug("Secret key algorithm is {}", secret.getAlgorithm());
        String jws = Jwts.builder()
                .header()
                .keyId(UUID.randomUUID().toString())
                .and()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(new Date())
                .subject(subject) // JSON Claims, or any byte[] content, with media type
                .audience().add(jwtProperties.getAudience())
                .and()
                .expiration(DateUtils.addSeconds(new Date(), jwtProperties.getExpiration()))
                .notBefore(new Date())
                .signWith(secret, Jwts.SIG.HS256)          // (4) if signing
                //.encryptWith(key, keyAlg, encryptionAlg) //     if encrypting
                .compact();
        log.info("Jws is {}", jws);
        return jws;
    }
}
