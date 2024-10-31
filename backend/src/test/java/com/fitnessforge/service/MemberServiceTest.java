package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fitnessforge.exception.DatabaseException;

import com.fitnessforge.entity.Member;
import com.fitnessforge.repository.MemberRepository;

public class MemberServiceTest {

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private BCryptPasswordEncoder encoder;

  @InjectMocks
  private MemberService memberService = new MemberServiceImpl();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void validCreateMemberTest() {
    Member member = new Member();
    String testEmail = "test@gmail.com";

    member.setEmail(testEmail);
    member.setPassword("hello");

    when(memberRepository.findByEmail(testEmail)).thenReturn(Optional.empty());
    when(memberRepository.save(member)).thenReturn(member);

    Member testMember = memberService.createNewMember(member);
    assertTrue(testMember.equals(member));
  }

  @Test
  public void memberAlreadyExistsTest() {
    Member member = new Member();
    String testEmail = "test@gmail.com";

    member.setEmail(testEmail);

    when(memberRepository.findByEmail(testEmail)).thenReturn(Optional.empty());
    when(memberRepository.save(member)).thenReturn(member);

    assertDoesNotThrow(() -> {
      memberService.createNewMember(member);
    });

    when(memberRepository.findByEmail(testEmail)).thenReturn(Optional.of(member));

    assertThrows(DatabaseException.class, () -> {
      memberService.createNewMember(member);
    });
  }

  @Test
  public void validGetMemberTest() {
    Long id = (long) 2;
    Member member = new Member();

    member.setId(id);
    member.setEmail("user@gmail.com");

    when(memberRepository.findByEmail("user@gmail.com")).thenReturn(Optional.empty());
    when(memberRepository.findById(id)).thenReturn(Optional.empty());
    when(memberRepository.save(member)).thenReturn(member);

    member = memberService.createNewMember(member);

    when(memberRepository.findById(id)).thenReturn(Optional.of(member));

    Member testMember = memberService.getMember(id);
    assertTrue(testMember.getEmail().equals(member.getEmail()));
  }

  @Test
  public void memberDoesNotExistsTest() {
    Long id = (long) 2;
    Member member = new Member();

    member.setId(id);
    member.setEmail("user@gmail.com");

    when(memberRepository.findByEmail("user@gmail.com")).thenReturn(Optional.empty());
    when(memberRepository.findById(id)).thenReturn(Optional.empty());
    when(memberRepository.save(member)).thenReturn(member);

    member = memberService.createNewMember(member);

    when(memberRepository.findById(id)).thenReturn(Optional.of(member));

    assertThrows(DatabaseException.class, () -> {
      memberService.getMember((long) 3);
    });
  }

  @Test
  public void setPasswordTest() {
    Member member = new Member();
    String testEmail = "test@gmail.com";
    String rawPassword = "hello";
    member.setEmail(testEmail);
    member.setPassword(rawPassword);

    String encodedPassword = "encodedPassword";

    when(memberRepository.findByEmail(testEmail)).thenReturn(Optional.empty());
    when(encoder.encode(rawPassword)).thenReturn(encodedPassword);
    when(memberRepository.save(member)).thenReturn(member);

    Member testMember = memberService.createNewMember(member);

    assertNotNull(testMember.getPassword());
    assertEquals(encodedPassword, testMember.getPassword());
  }

  @Test
  public void getMemberByEmailTest() {
    Member member = new Member();
    String email = "test@gmail.com";
    member.setEmail(email);

    when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());
    when(memberRepository.save(member)).thenReturn(member);

    memberService.createNewMember(member);

    when(memberRepository.findByEmail(email)).thenReturn(Optional.of(member));
    Member testMember = memberService.getMember(email);

    assertEquals(testMember.getEmail(), member.getEmail());
  }

  @Test
  public void getRefreshToken() {
    Member member = new Member();
    String testEmail = "test@gmail.com";
    Long id = (long) 1;

    member.setId(id);
    member.setEmail(testEmail);

    when(memberRepository.findById(id)).thenReturn(Optional.of(member));

    assertDoesNotThrow(() -> {
      String token = memberService.getRefreshToken(id);
      assertNotNull(token);
    });
  }

  @Test
  public void updateMemberTest() {
    Member member = new Member();

    Long id = 1L;
    String name = "test";
    String password = "test123";
    String email = "test@gmail.com";
    Date joinedOn = new Date();

    String fitnessLevel = "beginner";
    int age = 18;
    String gender = "male";
    float weight = 88.9f;
    float height = 5.11f;

    member.setId(id);
    member.setName(name);
    member.setPassword(password);
    member.setEmail(email);
    member.setJoinedOn(joinedOn);

    when(memberRepository.findById(id)).thenReturn(Optional.of(member));
    Member existingMember = memberService.getMember(id);

    assertNotNull(existingMember);

    member.setFitnessLevel(fitnessLevel);
    member.setAge(age);
    member.setGender(gender);
    member.setWeight(weight);
    member.setHeight(height);

    when(memberRepository.save(member)).thenReturn(member);

    Member testMember = memberService.updateMember(member);

    assertNotNull(testMember);

    assertEquals(testMember.getId(), id);
    assertEquals(testMember.getName(), name);
    assertEquals(testMember.getEmail(), email);
    assertEquals(testMember.getJoinedOn(), joinedOn);
    assertEquals(testMember.getFitnessLevel(), fitnessLevel);
    assertEquals(testMember.getAge(), age);
    assertEquals(testMember.getGender(), gender);
    assertEquals(testMember.getWeight(), weight);
    assertEquals(testMember.getHeight(), height);

    assertNull(testMember.getPassword());
  }
}
