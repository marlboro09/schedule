package com.sparta.schedule.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
	private static final String SECRET_KEY = "my_secret_key";
	private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

	public String createToken(String username, String role) {
		return Jwts.builder()
			.setSubject(username)
			.claim("role", role)
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
			.compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			return false;
		}
	}

	public Claims getUserInfoFromToken(String token) {
		return getClaims(token);
	}
}