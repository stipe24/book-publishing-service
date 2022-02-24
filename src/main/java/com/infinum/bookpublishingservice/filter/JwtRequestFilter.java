package com.infinum.bookpublishingservice.filter;

import com.infinum.bookpublishingservice.config.JsonWebConfiguration;
import com.infinum.bookpublishingservice.service.security.CustomUserDetailsService;
import com.infinum.bookpublishingservice.util.JsonWebUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
@EnableConfigurationProperties(JsonWebConfiguration.class)
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JsonWebConfiguration configuration;
    private final JsonWebUtil util;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String accessToken = request.getHeader("Authorization");

        if (accessToken != null) {
            var role = util.getSpecificClaimValueFromToken(accessToken, configuration.getRoleClaim());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(role);
            if (!util.isTokenExpired(accessToken) && util.isSignatureVerified(accessToken)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}