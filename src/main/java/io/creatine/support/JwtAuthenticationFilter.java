package io.creatine.support;

import io.creatine.user.api.TokenService;
import io.creatine.user.infrastructure.jpa.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = bearerToken.substring(7);
        String username = tokenService.extractSubject(token);
        var user = userRepository.findByUsername(username);
        var securityContext = SecurityContextHolder.getContext();

        if (user.isPresent() && securityContext.getAuthentication() == null) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetails(request));
            securityContext.setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
