package com.fitnessforge.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fitnessforge.security.filter.AuthenticationFilter;
import com.fitnessforge.security.filter.ExceptionHandlerFilter;
import com.fitnessforge.security.filter.JWTAuthorizationFilter;

import com.fitnessforge.security.manager.MemberAuthenticationManager;

/** 
 * <b>Description:</b>
 * <p>
 *  The configuration class which defines the configuration of the Spring security.
 * </p>
 * */
@Configuration
public class SecurityConfig {

  @Autowired
  private MemberAuthenticationManager authenticationManager; 

  /** 
   * Empty Default Constructor
   * */
  public SecurityConfig() {}

  /** 
   * Defines the configuration of the Spring security i.e
   * like which endpoints are allowed and session policies and etc.
   *
   * @param http An object of org.springframework.security.config.annotation.web.builders.HttpSecurity
   * @return An object of org.springframework.security.web.SecurityFilterChain
   * @throws Exception In case some internal error occurs due the config defined.
   * */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
    authenticationFilter.setFilterProcessesUrl("/auth");

    http.csrf((csrf) -> csrf.disable());
    http.cors();

    http.authorizeHttpRequests(
      (authorizeHttpRequests) ->
        authorizeHttpRequests
          .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH)
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/auth")
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

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow specific origin(s)
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setExposedHeaders(Arrays.asList("Authorization")); // Expose JWT in the Authorization header
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Apply the configuration to `/auth`
    
    return source;
  }
}
