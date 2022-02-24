package com.infinum.bookpublishingservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "application.authserver")
public class JsonWebConfiguration {

    private Integer port;
    private String keyUri;
    @Value("${application.authserver.claims.expiry}")
    private String expiryClaim;
    @Value("${application.authserver.claims.role}")
    private String roleClaim;

}