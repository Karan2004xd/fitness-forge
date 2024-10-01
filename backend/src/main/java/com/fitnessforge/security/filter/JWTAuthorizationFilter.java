package com.fitnessforge.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fitnessforge.security.SecurityConstants;

/** 
 * <b>Description:</b>
 * <p>
 *  Component of the Filter Chain of JWT (token based) authentication,
 *  which handles the verification of the JWT token provided by the user
 * </p>
 * */
public class JWTAuthorizationFilter extends OncePerRequestFilter {
  
  /** 
   * Empty Default Constructor
   * */
  public JWTAuthorizationFilter() {}

  /** 
   * Validates the JWT token provides in the Authorization header and then if valid or invalid
   * it generates an respective response with the releavant Status code back to the user by setting up the
   * response header.
   *
   * @param request Contains the header and request information made on the api endpoint
   * @param response Contains the methods to write a response back to the user
   * @param filterChain Contains the object of OncePerRequestFilter, for calling the next filter chain in sequence
   * */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String header = request.getHeader(SecurityConstants.AUTHORIZATION);

    if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = header.replace(SecurityConstants.BEARER, "");
    String member = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
      .build()
      .verify(token)
      .getSubject();

    Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, Arrays.asList());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
