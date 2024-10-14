package com.fitnessforge.security;

/** 
 * <b>Description:</b>
 * <p>
 *  An Constants class which contains constants which are used inside the security methods.
 *  {@link com.fitnessforge.security}
 * </p>
 * */
public class SecurityConstants {

  /** 
   * Empty Default Constructor
   * */
  public SecurityConstants() {}

  /** 
   * Contains the path which does not require JWT token for authorization
   * */
  public static final String REGISTER_PATH = "/member/*";

  /** 
   * Contains the Secret key which is used in generation of JWT token which is
   * obtained from the environment variable
   * */
  public static final String SECRET_KEY = System.getenv("SECRET_KEY");

  /** 
   * Contains the token expiration time of the token
   * */
  public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.

  /** 
   * Contains the helper string "Bearer "
   * */
  public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 

  /** 
   * Contains the helper string "Authorization"
   * */
  public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
}
