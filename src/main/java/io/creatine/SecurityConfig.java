package io.creatine;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.creatine.support.ErrorResponse;
import io.creatine.support.JwtAuthenticationFilter;
import io.creatine.account.api.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.time.ZonedDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] WHITE_LIST = new String[] {
            "/api/auth/**",
            "/api/foods",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**"
    };

    @Bean
    public JwtAuthenticationFilter authenticationFilter(TokenService tokenService,
                                                        UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(tokenService, userDetailsService);
    }

    @Bean
    public SecurityFilterChain security(HttpSecurity http,
                                        AuthenticationProvider authenticationProvider,
                                        AccessDeniedHandler accessDeniedHandler,
                                        AuthenticationEntryPoint authenticationEntryPoint,
                                        JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        var authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;

    }

    @Component
    @RequiredArgsConstructor
    private static class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

        private final ObjectMapper jacksonMapper;

        @Override
        public void commence(HttpServletRequest request,
                             HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            var errorResponse = new ErrorResponse(
                    authException.getMessage(),
                    URI.create(request.getServletPath()),
                    ZonedDateTime.now());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            var responseStream = response.getOutputStream();
            jacksonMapper.writeValue(responseStream, errorResponse);
            responseStream.flush();
        }
    }

    @Component
    @RequiredArgsConstructor
    private static class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

        private final ObjectMapper jacksonMapper;

        @Override
        public void handle(HttpServletRequest request,
                           HttpServletResponse response,
                           AccessDeniedException accessDeniedException) throws IOException {
            var errorResponse = new ErrorResponse(
                    accessDeniedException.getMessage(),
                    URI.create(request.getServletPath()),
                    ZonedDateTime.now());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            var responseStream = response.getOutputStream();
            jacksonMapper.writeValue(responseStream, errorResponse);
            responseStream.flush();
        }
    }

}
