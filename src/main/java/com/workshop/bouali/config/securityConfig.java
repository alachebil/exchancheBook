package com.workshop.bouali.config;

import com.workshop.bouali.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // bch n9oul lel spring eli hedhi security configuration class
@RequiredArgsConstructor // to have it  automatically in our constractor.
public class securityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDao userDao;


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

        http
                        .csrf().disable()
                        .authorizeRequests()
                        .antMatchers("/**/auth/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .sessionManagement()//zedet  kifeh nheb session ki naaml login soit to93ed msajla wal le w hedhi f star eli badou
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//statless =not saved  ; never= yetsajalech login ; always = login yetsajel
                        .and()
                        .authenticationProvider(authenticationProvider())
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }


    //nheb n9oul lel spring use userDetails eli 3maltha eni mech eli andek
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        final DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //9otlou yesta3mel password encoder eli implemntitou eni
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception {
        return config.getAuthenticationManager();
    }

    // methode lehya bel cryptage wal le
@Bean
    public PasswordEncoder passwordEncoder() {
       // return new  BCryptPasswordEncoder(); //nheb cryptage lel password nzid methode hedhi
    return NoOpPasswordEncoder.getInstance();//nheb password me8ir cryptage
    }


    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userDao.findUserByEmail(email);

            }
        };
    }
}
