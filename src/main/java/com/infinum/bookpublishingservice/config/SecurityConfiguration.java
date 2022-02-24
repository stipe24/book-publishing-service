package com.infinum.bookpublishingservice.config;

import com.infinum.bookpublishingservice.filter.JwtRequestFilter;
import com.infinum.bookpublishingservice.model.security.Role;
import com.infinum.bookpublishingservice.service.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService myUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/author").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/author").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/author").hasAnyAuthority(Role.ADMIN.name(), Role.AUTHOR.name(), Role.PUBLIC.name())
                .antMatchers(HttpMethod.GET, "/book").hasAnyAuthority(Role.ADMIN.name(), Role.AUTHOR.name(), Role.PUBLIC.name())
                .antMatchers(HttpMethod.POST, "/book").hasAuthority(Role.AUTHOR.name())
                .antMatchers( "/test").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}