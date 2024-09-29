package com.fitnessforge.utils;
import com.fitnessforge.entity.Member;
import com.fitnessforge.exception.DatabaseException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class FetchEntityUtilTest {
  
  @Test
  public void testForEntityFound() {
    Member member = new Member();
    Optional<Member> testObj = Optional.of(member);
    Member actualObj = FetchEntityUtil.GetEntity(testObj, Member.class);

    assertTrue(actualObj.equals(member));
  }

  @Test
  public void testForEntityNotFound() {
    Member member = null;
    Optional<Member> testObj = Optional.ofNullable(member);

    assertThrows(DatabaseException.class, () -> {
      FetchEntityUtil.GetEntity(testObj, Member.class);
    });
  }
}
