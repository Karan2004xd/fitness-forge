package com.fitnessforge.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.entity.Member;
import com.fitnessforge.service.MemberService;

import jakarta.validation.Valid;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Controller class for {@link com.fitnessforge.service.MemberService} services and entity
 * </p>
 * */
@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  /** 
   * Empty deafult constructor
   * */
  public MemberController() {}

  /** 
   * Provides the post mapping for creating a new {@link com.fitnessforge.entity.Member}.
   *
   * @param member an object of {@link com.fitnessforge.entity.Member} class
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @PostMapping("/create")
  public ResponseEntity<Map<String, String>> createNewMember(@Valid @RequestBody Member member) {
    Member savedMember = memberService.createNewMember(member);
    Map<String, String> responseToSend = new HashMap<>();

    responseToSend.put("id", savedMember.getId().toString());
    responseToSend.put("email", savedMember.getEmail());
    responseToSend.put("name", savedMember.getName());
    responseToSend.put("joinedOn", savedMember.getJoinedOn().toString());

    return new ResponseEntity<>(responseToSend, HttpStatus.CREATED);
  }

  /** 
   * Provides the get mapping for fetching {@link com.fitnessforge.entity.Member}
   * with its corresponding id.
   *
   * @param id an object of {@link com.fitnessforge.entity.Member} class
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/{id}")
  public ResponseEntity<Map<String, String>> getMember(@PathVariable Long id) {
    Member savedMember = memberService.getMember(id);
    Map<String, String> responseToSend = new HashMap<>();

    responseToSend.put("id", savedMember.getId().toString());
    responseToSend.put("name", savedMember.getName());
    responseToSend.put("age", String.valueOf(savedMember.getAge()));
    responseToSend.put("gender", savedMember.getGender());
    responseToSend.put("fitnessLevel", savedMember.getFitnessLevel());
    responseToSend.put("weight", String.valueOf(savedMember.getWeight()));
    responseToSend.put("height", String.valueOf(savedMember.getHeight()));
    responseToSend.put("email", savedMember.getEmail());
    responseToSend.put("joinedOn", savedMember.getJoinedOn().toString());

    return new ResponseEntity<>(responseToSend, HttpStatus.OK);
  }

  /** 
   * Provides the get mapping for fetching new refresh token with its corresponding id.
   *
   * @param id an object of {@link com.fitnessforge.entity.Member} class
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/auth_refresh/{id}")
  public ResponseEntity<String> getRefreshToken(@PathVariable Long id) {
    return new ResponseEntity<>(memberService.getRefreshToken(id), HttpStatus.OK);
  }
}
