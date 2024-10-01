package com.fitnessforge.service;

import com.fitnessforge.entity.Member;

public interface MemberService {
  Member createNewMember(Member member);
  Member getMember(Long id);
  Member getMember(String email);
  String getRefreshToken(Long id);
}
