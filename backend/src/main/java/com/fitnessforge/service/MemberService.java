package com.fitnessforge.service;

import java.util.List;

import com.fitnessforge.entity.Member;

/** 
 * <b>Description:</b>
 * <p>
 *  The interface providing services for {@link com.fitnessforge.entity.Member}
 * </p>
 * */
public interface MemberService {

  /** 
   * Creates a new member in the database while checking if not duplicate.
   *
   * @param member object of class {@link com.fitnessforge.entity.Member}
   * @return object of class {@link com.fitnessforge.entity.Member}
   * */
  Member createNewMember(Member member);

  /** 
   * Fetchs the existing member from the database using the corresponding id
   *
   * @param id id of class {@link com.fitnessforge.entity.Member}
   * @return object of class {@link com.fitnessforge.entity.Member}
   * */
  Member getMember(Long id);

  /** 
   * Fetchs the existing member from the database using the corresponding email 
   *
   * @param email email of class {@link com.fitnessforge.entity.Member}
   * @return object of class {@link com.fitnessforge.entity.Member}
   * */
  Member getMember(String email);

  /** 
   * Generates a new JWT token
   *
   * @param id id of class {@link com.fitnessforge.entity.Member}
   * @return String of new JWT token
   * */
  String getRefreshToken(Long id);

  /** 
   * Updates the existing member with the new member passed from the request
   *
   * @param member an object of entity {@link com.fitnessforge.entity.Member}
   * @return object of class {@link com.fitnessforge.entity.Member}
   * */
  Member updateMember(Member member);

  /** 
   * Fetches the list of workout ids associated with the
   * member of the provided id.
   *
   * @param id the id of {@link com.fitnessforge.entity.Member} entity
   * @return an list of ids of {@link com.fitnessforge.entity.Workout} entity
   * */
  List<Long> getWorkoutIds(Long id);
}
