package com.example.demo.security;

import com.example.demo.security.Jwt.AuthEntryPointJwt;
import com.example.demo.security.Jwt.AuthTokenFilter;
import com.example.demo.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class WebSecurityConfig {

    private final AuthTokenFilter authTokenFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig(AuthTokenFilter authTokenFilter,
                             UserDetailsServiceImpl userDetailsService,
                             AuthEntryPointJwt unauthorizedHandler) {
        this.authTokenFilter = authTokenFilter;
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for stateless APIs (necessary for JWT-based authentication)
                .csrf(csrf -> csrf.disable())

                // Set custom unauthorized handler
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))

                // Stateless session because we use JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))  // Enable CORS

                // Define URL access rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/**").permitAll()// Public endpoints for login/signup
                        .requestMatchers("/api/**").permitAll()  // Any other request requires authentication
                );

        // Add JWT token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuring AuthenticationManager with UserDetailsService and PasswordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder to hash and verify passwords
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(); // The filter that checks the JWT in requests
    }
}
