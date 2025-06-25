package cz.jsdh.app.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Konfigurace bezpečnostního filtru (security filter chain).
     * Definuje pravidla přístupů, login, logout a další bezpečnostní aspekty.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Definice pravidel přístupů dle URL a rolí
                .authorizeHttpRequests(auth -> auth
                        // Veřejné zdroje, přihlašovací stránka a statické soubory
                        .requestMatchers("/logout-success", "/login", "/css/**", "/js/**", "/img/**").permitAll()
                        // Role ADMIN přístup k /admin/**
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Role VELITEL a ADMIN přístup k /velitel/**
                        .requestMatchers("/velitel/**").hasAnyRole("VELITEL", "ADMIN")
                        // Role HASIC, VELITEL a ADMIN přístup k /hasic/**
                        .requestMatchers("/hasic/**").hasAnyRole("HASIC", "VELITEL", "ADMIN")
                        // Ostatní požadavky autentizované
                        .anyRequest().authenticated()
                )
                // Konfigurace přihlašování
                .formLogin(form -> form
                        .loginPage("/login")            // vlastní přihlašovací stránka
                        .defaultSuccessUrl("/dashboard", true) // přesměrování po úspěšném přihlášení
                        .permitAll()
                )
                // Konfigurace odhlašování
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Bean AuthenticationManager – používá se při autentizaci uživatele.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * PasswordEncoder pro šifrování hesel.
     * Aktuálně je zde NoOpPasswordEncoder (hesla nejsou šifrovaná).
     * Doporučuji v budoucnu použít BCryptPasswordEncoder pro bezpečnost.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();

    }
}
