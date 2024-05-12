package com.nnk.springboot.configuration;

import com.nnk.springboot.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        ;
        return http.build();
    }
}