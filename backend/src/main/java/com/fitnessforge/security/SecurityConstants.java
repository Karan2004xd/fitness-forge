package com.fitnessforge.security;

public class SecurityConstants {
  public static final String REGISTER_PATH = "/member/*";
  public static final String TOKEN_PATH = "/token/*";

  public static final String SECRET_KEY = "bQeThWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)"; //Your secret should always be strong (uppercase, lowercase, numbers, symbols) so that nobody can potentially decode the signature.
  public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
  public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 
  public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
  
  public static final int FIRST_TOKEN_EXPIRATION_IN_HOURS = 2; // 2 hours
  public static final int REFRESH_TOKEN_EXPIRATION_IN_HOURS = 24; // 24 hours
}
