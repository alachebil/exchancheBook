package com.workshop.bouali.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.*;

@EnableWebSecurity // bch n9oul lel spring eli hedhi security configuration class
@RequiredArgsConstructor // to have it in our automatically constractor.
public class securityConfig {

    private final JwtAthFilter jwtAthFilter;
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("Bouali.social@gmail.com",
            "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("user.social@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))


            );


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().anyRequest()).authenticated()
                .and().addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return null;
            }
        };
    }
}
