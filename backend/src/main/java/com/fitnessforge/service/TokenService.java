package com.fitnessforge.service;

import com.fitnessforge.entity.Token;

public interface TokenService {
  Token createNewToken(Token token);
  Token getToken(Long memberId);
  Token getRefreshToken(Long memberId);
}
