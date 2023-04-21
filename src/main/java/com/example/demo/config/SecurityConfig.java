package com.example.demo.config;

import com.example.demo.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf().disable()
                .authorizeHttpRequests()
                .regexMatchers("/person/add_person").permitAll()
                .regexMatchers("/person/add_publisher").hasRole("ADMIN")
                .regexMatchers("/person/add_author").hasRole("ADMIN")
                .regexMatchers("/person/add_book").hasRole("ADMIN")
                .regexMatchers("/person/show_books").hasRole("ADMIN")
                .anyRequest().authenticated().and().httpBasic();
        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService((username -> personRepository.findUserByUsername(username).
                        orElseThrow(() -> new UsernameNotFoundException(String.format("This %s notFound!", username))))).
                passwordEncoder(bCryptPasswordEncoder);
    }
}
