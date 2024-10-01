package com.fitnessforge.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fitnessforge.security.SecurityConstants;

/** 
 * <b>Description:</b>
 * <p>
 *  Utility class for Generating JWT tokens
 * </p>
 * */
public class JWTTokenGeneratorUtil {

  /** 
   * Empty default constructor
   * */
  public JWTTokenGeneratorUtil() {}

  /** 
   * Helper utility for generating a refresh JWT Token
   *
   * @param principleName subject or principle of the JWT token generation
   * @return String of a new refresh JWT token.
   * */
  public static String getNewJWTToken(String principleName) {
    String token = JWT.create()
      .withSubject(principleName)
      .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
      .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

    return token;
  }
}
