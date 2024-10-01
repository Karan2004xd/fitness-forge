package com.fitnessforge.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fitnessforge.exception.DatabaseException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * <b>Description:</b>
 * <p>
 *  Component of the Filter Chain of JWT (token based) authentication,
 *  which handles the initial exception handling of the authentication process.
 * </p>
 * */
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  /** 
   * Empty Default Constructor
   * */
  public ExceptionHandlerFilter() {}

  /** 
   * Handles the basic exception handling of cases like if the provided user does not exist,
   * or the token presented is in valid or not, and provides the necessary response with proper
   * status code back to the user with the error message
   *
   * @param request Contains the header and request information made on the api endpoint
   * @param response Contains the methods to write a response back to the user
   * @param filterChain Contains the object of OncePerRequestFilter, for calling the next filter chain in sequence
   * */
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
