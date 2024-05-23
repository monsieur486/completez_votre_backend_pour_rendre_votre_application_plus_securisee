package com.nnk.springboot.configuration;

import com.nnk.springboot.services.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * WebSecurity is a configuration class that sets up the security configurations for the application.
 * It enables web security and method level security, and provides beans for user details service, password encoder,
 * authentication provider and security filter chain.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurity {

    /**
     * This method provides a bean for the user details service.
     *
     * @return a new instance of AppUserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    /**
     * This method provides a bean for the password encoder.
     *
     * @return a new instance of BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method provides a bean for the authentication provider.
     *
     * @return a new instance of DaoAuthenticationProvider configured with the user details service and password encoder
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * This method provides a bean for the security filter chain.
     * It configures the HTTP security including authorization of requests, form login, logout, HTTP Basic authentication, CSRF and headers.
     *
     * @param http the HttpSecurity to modify
     * @return the SecurityFilterChain built from the HttpSecurity
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/app/**")).permitAll();
                    req.requestMatchers(new AntPathRequestMatcher("/user/**")).hasAuthority("USER_MANAGEMENT");
                    req.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/app/login")
                        .defaultSuccessUrl("/bidList/list")
                )
                .logout(logout -> logout.logoutSuccessUrl("/app/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // h2-console
        ;
        return http.build();
    }
}