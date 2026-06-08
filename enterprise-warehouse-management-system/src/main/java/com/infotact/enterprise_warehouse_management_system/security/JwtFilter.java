package com.infotact.enterprise_warehouse_management_system.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.startsWith("/auth") || uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs")
				|| uri.startsWith("/api") || uri.startsWith("/orders") || uri.startsWith("/products")
				|| uri.startsWith("/inventory") || uri.startsWith("/warehouses") || uri.startsWith("/storagebins")) {

			filterChain.doFilter(request, response);
			return;
		}
	}
}