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

@Component
public class MemberAuthenticationManager implements AuthenticationManager {
  
  @Autowired
  private MemberService memberService;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Member member = memberService.getMember(authentication.getName());

    if (!encoder.matches(authentication.getCredentials().toString(), member.getPassword())) {
      throw new BadCredentialsException("You provided wrond credentials");
    }

    return new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
  }
}
