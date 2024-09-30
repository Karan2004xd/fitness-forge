package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
  Optional<Token> findByMemberId(Long memberId);
}
