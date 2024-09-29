package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
  Optional<Member> findById(Long id);
  Optional<Member> findByEmail(String email);
}
