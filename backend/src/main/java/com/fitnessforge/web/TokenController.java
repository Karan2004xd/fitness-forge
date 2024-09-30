package com.fitnessforge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @GetMapping("/refresh/{memberId}")
  public ResponseEntity<String> refreshToken(@PathVariable Long memberId) {
    return new ResponseEntity<>(tokenService.getRefreshToken(memberId).getTokenString(), HttpStatus.OK);
  }
}
