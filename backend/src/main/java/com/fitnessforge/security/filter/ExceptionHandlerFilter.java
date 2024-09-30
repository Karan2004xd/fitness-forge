package com.fitnessforge.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fitnessforge.exception.DatabaseException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      filterChain.doFilter(request, response);
    } catch (DatabaseException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("Username doesn't exist");
      response.getWriter().flush();
    } catch (JWTVerificationException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write("JWT token is not valid");
      response.getWriter().flush();
    } catch (RuntimeException e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Bad Request");
      response.getWriter().flush();
    }
  }
}
