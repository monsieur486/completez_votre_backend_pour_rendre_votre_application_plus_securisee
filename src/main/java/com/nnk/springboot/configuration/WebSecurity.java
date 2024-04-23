package com.nnk.springboot.configuration;

import com.nnk.springboot.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/app/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/login")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/bidList/**")).hasRole("USER");
                    req.requestMatchers(new AntPathRequestMatcher("/rating/**")).hasRole("USER");
                    req.requestMatchers(new AntPathRequestMatcher("/ruleName/**")).hasRole("USER");
                    req.requestMatchers(new AntPathRequestMatcher("/trade/**")).hasRole("USER");
                    req.requestMatchers(new AntPathRequestMatcher("/curvePoint/**")).hasRole("USER");
                    req.requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("ADMIN");
                    req.anyRequest().authenticated();
                })
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/login"))
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
