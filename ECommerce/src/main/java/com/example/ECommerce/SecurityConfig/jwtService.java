package com.example.ECommerce.SecurityConfig;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class jwtService {
	//*************JWT Generation*****************
	

		private static final String SECRET_KEY = "04c10ff80b9361b54aeffacb6e77f4325414cd4d3ca08fd3937a864d240e41d8";

		public String generateToken(UserDetails user) {
			return generateToken(new HashMap<>(), user);
		}
		
		
		public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
			return Jwts.builder()
					.setClaims(extraClaims)
					.setSubject(user.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 10000*60*60*24))
					.signWith(getSignInKey(), SignatureAlgorithm.HS256)
					.compact();
		}
		
		
		
		//*************Claim Resolver*****************
		
		
		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
		

		public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}

		private Claims extractAllClaims(String token) {
			return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		}

		private Key getSignInKey() {
			byte keybytes[] = Decoders.BASE64.decode(SECRET_KEY);
			return Keys.hmacShaKeyFor(keybytes);
		}

		
		
		
		
		
		//*************JWT Validation*****************
		
		public boolean isTokenValid(String token, UserDetails user) {
		    final String username = extractUsername(token);
		    return (username.equals(user.getUsername())) && !isTokenExpired(token);
		  }
		
		private boolean isTokenExpired(String token) {
		    return extractExpiration(token).before(new Date());
		  }

		  private Date extractExpiration(String token) {
		    return extractClaim(token, Claims::getExpiration);
		  }
	
}
