package com.fitnessforge.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fitnessforge.service.MemberService;
import com.fitnessforge.entity.Member;

/** 
 * <b>Description:</b>
 * <p>
 *  An Custom Authentication manager for authenticating the member trying to access endpoints
 *  which are not open to access without a JWT token.
 * </p>
 * */
@Component
public class MemberAuthenticationManager implements AuthenticationManager {
  
  @Autowired
  private MemberService memberService;

  @Autowired
  private BCryptPasswordEncoder encoder;

  /** 
   * Empty Default Constructor
   * */
  public MemberAuthenticationManager() {}

  /** 
   * Authenticates the member using the credentials provided in the authentication object 
   * (passed from the {@link com.fitnessforge.security.filter.AuthenticationFilter} filter)
   * @param authentication An object of class org.springframework.security.core.Authentication
   * @return Returns an object of org.springframework.security.authentication.UsernamePasswordAuthenticationToken
   * @throws BadCredentialsException if the credentials are invalid.
   * */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Member member = memberService.getMember(authentication.getName());

    if (!encoder.matches(authentication.getCredentials().toString(), member.getPassword())) {
      throw new BadCredentialsException("You provided wrong credentials");
    }

    return new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
  }
}
