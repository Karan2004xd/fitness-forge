package com.fitnessforge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.fitnessforge.security.filter.AuthenticationFilter;
import com.fitnessforge.security.filter.ExceptionHandlerFilter;
import com.fitnessforge.security.filter.JWTAuthorizationFilter;

import com.fitnessforge.security.manager.MemberAuthenticationManager;

@Configuration
public class SecurityConfig {

  @Autowired
  private MemberAuthenticationManager authenticationManager; 

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
    authenticationFilter.setFilterProcessesUrl("/auth");

    http.csrf((csrf) -> csrf.disable());

    http.authorizeHttpRequests(
      (authorizeHttpRequests) ->
        authorizeHttpRequests
          .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH)
          .permitAll()
          .requestMatchers(HttpMethod.GET, SecurityConstants.TOKEN_PATH)
          .permitAll()
          .anyRequest()
          .authenticated()
    );

    http
      .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
      .addFilter(authenticationFilter)
      .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

    http
      .sessionManagement(
        (sessionManagement) -> 
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );

    return http.build();
  }
}
