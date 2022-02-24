package com.infinum.bookpublishingservice.util;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.infinum.bookpublishingservice.config.JsonWebConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

@Component
@EnableConfigurationProperties(JsonWebConfiguration.class)
@AllArgsConstructor
@Slf4j
public class JsonWebUtil {

    private final JsonWebConfiguration configuration;

    public boolean isSignatureVerified(String accessToken) {
        try {
            log.info("Verifying signature...");
            JwkProvider provider = new JwkProviderBuilder(new URL(configuration.getKeyUri().replace(":getPort", configuration.getPort().toString()))).build();
            var decodedJWT = decodeAccessToken(accessToken);
            Jwk jwk = provider.get(decodedJWT.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            Verification verification = JWT.require(algorithm);
            verification.build().verify(decodedJWT);
            log.info("Signature verified!");
            return true;
        } catch (MalformedURLException | JwkException e) {
            log.error("An error occurred while verifying signature.");
            return false;
        }
    }

    public String getSpecificClaimValueFromToken(String accessToken, String claimName) {
        var decodedJwt = decodeAccessToken(accessToken);
        return String.valueOf(decodedJwt.getClaims().get(claimName));
    }

    public boolean isTokenExpired(String accessToken) {
        var expiry = Long.parseLong(getSpecificClaimValueFromToken(accessToken, configuration.getExpiryClaim()));
        var now = Instant.now().toEpochMilli() / 1000L;
        return expiry < now;
    }

    private static DecodedJWT decodeAccessToken(String accessToken) {
        return JWT.decode(accessToken);
    }

}