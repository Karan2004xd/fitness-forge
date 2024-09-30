package com.fitnessforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fitnessforge.entity.Token;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseException.DatabaseExceptionTypes;
import com.fitnessforge.repository.TokenRepository;
import com.fitnessforge.security.SecurityConstants;
import com.fitnessforge.utils.FetchEntityUtil;
import com.fitnessforge.utils.JWTTokenGeneratorUtil;

@Service
public class TokenServiceImpl implements TokenService {

  @Autowired
  private TokenRepository tokenRepository;

  @Autowired
  private MemberService memberService;

  @Override
  public Token createNewToken(Token token) {
    try {
      return tokenRepository.save(token);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(DatabaseExceptionTypes.CONSTRAINT_VIOLATIONS, TokenService.class.getName());
    }
  }

  @Override
  public Token getToken(Long memberId) {
    return FetchEntityUtil.GetEntity(tokenRepository.findByMemberId(memberId), Token.class);
  }

  @Override
  public Token getRefreshToken(Long memberId) {
    String principleString = memberService.getMember(memberId).getEmail();
    String refreshedTokenString = JWTTokenGeneratorUtil.getNewJWTToken(principleString);

    Token refreshedToken = new Token(
      refreshedTokenString,
      SecurityConstants.REFRESH_TOKEN_EXPIRATION_IN_HOURS,
      memberId
    );
    return createNewToken(refreshedToken);
  }
}
