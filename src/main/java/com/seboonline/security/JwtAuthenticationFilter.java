package com.seboonline.security;


import com.seboonline.repositories.TokenRepository;
import com.seboonline.services.JwtService;
import com.seboonline.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");
            if (!StringUtils.isEmpty(authHeader) || StringUtils.startsWith(authHeader, "Bearer ")) {
                authenticate(authHeader, request);
            }
            filterChain.doFilter(request, response);
        }

        catch (ExpiredJwtException e) {
            sendErrorResponse(response, "Token expired");
        }
        catch (UsernameNotFoundException e) {
            sendErrorResponse(response, "Bad credentials");
        }


    }

    private void authenticate(String authHeader, HttpServletRequest request) {
        final String jwt, userName;

        jwt = authHeader.substring(7);
        userName = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(userName)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(userName);

            if (jwtService.isTokenValid(jwt, userDetails) && this.tokenRepository.existsByToken(jwt)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        String payload = "{\"error\":\""+message+"\"}";
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("content-type", "application/json");
        response.getWriter().write(payload);
        response.getWriter().flush();
        response.getWriter().close();
    }




}