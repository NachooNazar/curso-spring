package com.example.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        * Autorizo request -> url = /api/laptops -> entra cualquiera
        * Autorizo request -> url = /api/laptops/delete -> solo admin
        * Cualquier request que este autenticada y este logueada
        * */
    http.authorizeRequests()
            .antMatchers("/api/laptops").permitAll()
            .antMatchers("/api/laptops/delete").hasRole("ADMIN")
            .anyRequest().authenticated().and().formLogin().and().httpBasic();

    return http.build();
    }

    @Bean
    public UserDetailsService userdetailsService(){

        /*
        * Defino un user/admin que con x pass y x user tenga x rol.
        * A continacion los agrego a memoria
        * */
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .roles("USER").build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
