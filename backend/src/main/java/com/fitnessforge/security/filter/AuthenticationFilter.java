package com.fitnessforge.security.filter;

import com.fitnessforge.security.manager.MemberAuthenticationManager;
import com.fitnessforge.utils.JWTTokenGeneratorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnessforge.entity.Member;
import com.fitnessforge.security.SecurityConstants;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private MemberAuthenticationManager authenticationManager;

  public AuthenticationFilter(MemberAuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      Member member = new ObjectMapper().readValue(request.getInputStream(), Member.class);
      Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
      return authenticationManager.authenticate(authentication);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    String token = JWTTokenGeneratorUtil.getNewJWTToken(authResult.getName());
    response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(failed.getMessage());
    response.getWriter().flush();
  }
}
