package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fitnessforge.entity.Token;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.repository.TokenRepository;

public class TokenServiceTest {

  @Mock
  private TokenRepository tokenRepository;

  @InjectMocks
  private TokenService tokenService = new TokenServiceImpl();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void createNewTokenTest() {
    Token token = new Token();
    String testString = "some test string";

    token.setId((long) 1);
    token.setTokenString(testString);
    token.setValidHours(5);

    when(tokenRepository.save(token)).thenReturn(token);

    Token testToken = tokenService.createNewToken(token);

    assertEquals(testToken.getTokenString(), testString);
    assertEquals(testToken.getValidHours(), token.getValidHours());
    assertEquals(testToken.getId(), token.getId());
  }

  @Test
  public void getTokenByMemberIdTest() {
    Token token = new Token();
    String testString = "some test string";
    Long memberId = (long) 1;

    token.setId((long) 1);
    token.setTokenString(testString);
    token.setValidHours(5);

    when(tokenRepository.save(token)).thenReturn(token);

    tokenService.createNewToken(token);

    when(tokenRepository.findByMemberId(memberId)).thenReturn(Optional.of(token));
    Token testToken = tokenService.getToken(memberId);

    assertNotNull(testToken);
    assertEquals(testToken.getId(), token.getId());
    assertEquals(testToken.getTokenString(), testString);
    assertEquals(testToken.getValidHours(), token.getValidHours());
  }

  @Test
  public void getNotExistingTokenByMemberTest() {
    Token token = new Token();
    String testString = "some test string";
    Long memberId = (long) 1;

    token.setId((long) 1);
    token.setTokenString(testString);
    token.setValidHours(5);

    when(tokenRepository.save(token)).thenReturn(token);

    tokenService.createNewToken(token);

    when(tokenRepository.findByMemberId(memberId)).thenReturn(Optional.of(token));

    assertThrows(DatabaseException.class, () -> {
      tokenService.getToken((long) 2);
    });
  }
}
