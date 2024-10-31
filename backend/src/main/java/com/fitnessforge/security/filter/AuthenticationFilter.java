package com.fitnessforge.security.filter;

import com.fitnessforge.security.manager.MemberAuthenticationManager;
import com.fitnessforge.service.MemberService;
import com.fitnessforge.utils.JWTTokenGeneratorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnessforge.entity.Member;
import com.fitnessforge.security.SecurityConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * <b>Description:</b>
 * <p>
 *  Component of the Filter Chain of JWT (token based) authentication,
 *  which handles the authentication process of the user and token as well.
 * </p>
 * */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  /** 
   * Empty Default Constructor
   * */
  public AuthenticationFilter() {}

  @Autowired
  private MemberAuthenticationManager authenticationManager;

  @Autowired
  private MemberService memberService;

  /** 
   * Sets the Object of the authentication manager {@link com.fitnessforge.security.manager.MemberAuthenticationManager}
   * @param authenticationManager An object of custom authentication Manager 
   * {@link com.fitnessforge.security.manager.MemberAuthenticationManager}
   * */
  public AuthenticationFilter(MemberAuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  /** 
   * Sets the Object of the authentication manager {@link com.fitnessforge.security.manager.MemberAuthenticationManager}
   * and the Object of Member Service, an object of the Member Entity
   * {@link com.fitnessforge.service.MemberService}
   * 
   * @param authenticationManager An object of custom authentication Manager 
   * {@link com.fitnessforge.security.manager.MemberAuthenticationManager}
   * @param memberService An object of the Member Entity
   * {@link com.fitnessforge.service.MemberService}
   * */
  public AuthenticationFilter(MemberAuthenticationManager authenticationManager, MemberService memberService) {
    this.authenticationManager = authenticationManager;
    this.memberService = memberService;
  }

  /** 
   * This methods gets invocked when the filter is first time called.
   * @param request Contains the header and request information made on the api endpoint
   * @param response Contains the methods to write a response back to the user
   * @return An Object of the {@link com.fitnessforge.security.manager.MemberAuthenticationManager}
   * */
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

  /** 
   * This methods gets invocked if the authentication is successfull and generates the
   * JWT token using the {@link com.fitnessforge.utils.JWTTokenGeneratorUtil} class and the JWT token to
   * the header under the Authorization tag.
   * @param request Contains the header and request information made on the api endpoint
   * @param response Contains the methods to write a response back to the user
   * */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    String token = JWTTokenGeneratorUtil.getNewJWTToken(authResult.getName());
    Member member = memberService.getMember(authResult.getName());

    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("id", member.getId().toString());
    
    response.setContentType("application/json");
    response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
    new ObjectMapper().writeValue(response.getOutputStream(), responseMap);
  }

  /** 
   * This methods gets invoked if the authentication was not successfull and generates an
   * Status code of UNAUTHORIZED (401) with the error message received from the exception
   * @param request Contains the header and request information made on the api endpoint
   * @param response Contains the methods to write a response back to the user
   * @param failed Contains the methods to get the exception message.
   * */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(failed.getMessage());
    response.getWriter().flush();
  }
}
