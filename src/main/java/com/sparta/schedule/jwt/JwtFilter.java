package com.sparta.schedule.jwt;

import com.sparta.schedule.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JwtFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		String token = request.getHeader("Authorization");

		if (token != null && !token.isEmpty()) {
			if (jwtService.validateToken(token)) {
				Claims claims = jwtService.getUserInfoFromToken(token);
				request.setAttribute("username", claims.getSubject());
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token");
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}