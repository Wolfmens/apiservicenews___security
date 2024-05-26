package com.study.apiservicenews.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(HttpSecurity security,
                                                       UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        var authManagerBuilder = security.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService);

        var authProvider = new DaoAuthenticationProvider(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);

        authManagerBuilder.authenticationProvider(authProvider);

        return authManagerBuilder.build();
    }

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity security,
                                                   AuthenticationManager authenticationManager) {
        security
                .authorizeHttpRequests((auth) -> auth
                        //client requests
                        .requestMatchers(HttpMethod.GET, "/news/client").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/news/client/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.PUT, "/news/client/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/news/client/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.POST, "/news/client").permitAll()
                        //novelty cat. requests
                        .requestMatchers(HttpMethod.GET, "/news/novcat/**").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.POST, "/news/novcat").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.PUT, "/news/novcat/{id}").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/news/novcat/{id}").hasAnyRole("ADMIN", "MODERATOR")
                        //novelty requests
                        .requestMatchers(HttpMethod.GET, "/news/novelty/**").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.POST, "/news/novelty").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.PUT, "/news/novelty/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/news/novelty/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        //novelty comments requests
                        .requestMatchers(HttpMethod.GET, "/news/novcom/**").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.POST, "/news/novcom").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.PUT, "/news/novcom/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/news/novcom/{id}").hasAnyRole("ADMIN", "MODERATOR", "USER")
                ).csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement
                        (httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager);

        return security.build();
    }
}


