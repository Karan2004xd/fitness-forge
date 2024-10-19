package com.fitnessforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnessforge.entity.Member;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseExceptionTypes;
import com.fitnessforge.repository.MemberRepository;
import com.fitnessforge.utils.FetchEntityUtil;
import com.fitnessforge.utils.JWTTokenGeneratorUtil;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Implementation class for interface {@link com.fitnessforge.service.MemberService}
 * </p>
 * */
@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  /** 
   * Empty default constructor
   * */
  public MemberServiceImpl() {}

  /** 
   * Checks if the member exists in the database or not
   *
   * @param member an object of {@link com.fitnessforge.entity.Member}
   * @return Boolean if the member exists or not
   * */
  private boolean checkIfMemberExists(Member member) {
    try {
      FetchEntityUtil.GetEntity(memberRepository.findByEmail(member.getEmail()), Member.class);
    } catch (DatabaseException e) {
      return false;
    }
    return true;
  }

  /** 
   * Creates new member in the database while checking if the member already exists or not
   *
   * @param member an object of {@link com.fitnessforge.entity.Member}
   * @return an object of {@link com.fitnessforge.entity.Member}
   * @throws DatabaseException if the member does not exist
   * @throws DataIntegrityViolationException if any database constraint are violeted
   * */
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

  /** 
   * Fetches the {@link com.fitnessforge.entity.Member} from the database
   *
   * @param id of {@link com.fitnessforge.entity.Member} class
   * @return an object of {@link com.fitnessforge.entity.Member}
   * @throws DatabaseException from {@link com.fitnessforge.utils.FetchEntityUtil}
   * */
  @Override
  public Member getMember(Long id) {
    return FetchEntityUtil.GetEntity(memberRepository.findById(id), Member.class);
  }

  /** 
   * Fetches the {@link com.fitnessforge.entity.Member} from the database
   *
   * @param email of {@link com.fitnessforge.entity.Member} class
   * @return an object of {@link com.fitnessforge.entity.Member}
   * @throws DatabaseException from {@link com.fitnessforge.utils.FetchEntityUtil}
   * */
  @Override
  public Member getMember(String email) {
    return FetchEntityUtil.GetEntity(memberRepository.findByEmail(email), Member.class);
  }

  /** 
   * Generates a new JWT refresh token, using the util {@link com.fitnessforge.utils.JWTTokenGeneratorUtil}
   *
   * @param id of {@link com.fitnessforge.entity.Member} class
   * @return String of new JWT token
   * */
  @Override
  public String getRefreshToken(Long id) {
    try {
      Member member = FetchEntityUtil.GetEntity(memberRepository.findById(id), Member.class);
      return JWTTokenGeneratorUtil.getNewJWTToken(member.getEmail());
    } catch (DatabaseException e) {
      throw new DatabaseException(DatabaseExceptionTypes.MEMBER_NOT_FOUND, MemberService.class.getName());
    }
  }

  @Override
  public Member updateMember(Member member) {
    Member existingMember = FetchEntityUtil.GetEntity(memberRepository.findById(member.getId()), Member.class);
    if (existingMember != null) {
      member.setPassword(existingMember.getPassword());
      Member memberToReturn = memberRepository.save(member);
      memberToReturn.setPassword(null);
      return memberToReturn;
    }
    return null;
  }
}
