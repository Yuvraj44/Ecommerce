package com.example.ECommerce.SecurityConfig;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class jwtFilter extends OncePerRequestFilter {

	private final jwtService jwtServiceobj;
	private final UserDetailsService userDetailsobj;


	@Override
	protected void doFilterInternal( HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException 
	{
		
		//Step1: if jwt exists and get the jwt
		final String authHeader = request.getHeader("Authorization");
		final String jwt;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) 
		{
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);

		//Step2: Check if user exists in DB using UserDetailService

		final String phoneNumber;
		phoneNumber = jwtServiceobj.extractUsername(jwt);
		
		//Step3: Update Security Context Holder
		if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) // user exists but authentication is not done
		{
			UserDetails user = this.userDetailsobj.loadUserByUsername(phoneNumber);

			if (jwtServiceobj.isTokenValid(jwt, user)) 
			{
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}