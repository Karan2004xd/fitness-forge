package com.fitnessforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnessforge.entity.Member;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseException.DatabaseExceptionTypes;
import com.fitnessforge.repository.MemberRepository;
import com.fitnessforge.utils.FetchEntityUtil;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  private boolean checkIfMemberExists(Member member) {
    try {
      FetchEntityUtil.GetEntity(memberRepository.findByEmail(member.getEmail()), Member.class);
    } catch (DatabaseException e) {
      return false;
    }
    return true;
  }

  @Override
  public Member createNewMember(Member member) {
    try {
      if (!checkIfMemberExists(member)) {
        member.setPassword(encoder.encode(member.getPassword()));
        return memberRepository.save(member);
      }
      throw new DatabaseException(DatabaseExceptionTypes.MEMBER_ALREADY_EXISTS, MemberService.class.getName());
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(DatabaseExceptionTypes.CONSTRAINT_VIOLATIONS, MemberService.class.getName());
    }
  }

  @Override
  public Member getMember(Long id) {
    return FetchEntityUtil.GetEntity(memberRepository.findById(id), Member.class);
  }

  @Override
  public Member getMember(String email) {
    return FetchEntityUtil.GetEntity(memberRepository.findByEmail(email), Member.class);
  }
}
