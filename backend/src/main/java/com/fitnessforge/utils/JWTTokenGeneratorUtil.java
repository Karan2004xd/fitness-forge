package com.fitnessforge.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fitnessforge.security.SecurityConstants;

public class JWTTokenGeneratorUtil {
  public static String getNewJWTToken(String principleName) {
    String token = JWT.create()
      .withSubject(principleName)
      .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
      .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

    return token;
  }
}
