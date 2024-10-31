package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Member;

/** 
 * <b>Description:</b>
 * <p>
 *  Interface which provides the methods to perform CRUD operation on the database 
 *  table entity {@link com.fitnessforge.entity.Member} 
 * </p>
 * */
@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

  /** 
   * Fetchs the {@link com.fitnessforge.entity.Member} object by its id 
   * (if it is valid and if it exists in the database)
   * @param id The id of the existing member
   * @return the {@link com.fitnessforge.entity.Member} class object (if exists)
   * */
  Optional<Member> findById(Long id);

  /** 
   * Fetchs the {@link com.fitnessforge.entity.Member} object by its email
   * (if it is valid and if it exists in the database)
   * @param email The email of the existing member
   * @return the {@link com.fitnessforge.entity.Member} class object (if exists)
   * */
  Optional<Member> findByEmail(String email);
}
