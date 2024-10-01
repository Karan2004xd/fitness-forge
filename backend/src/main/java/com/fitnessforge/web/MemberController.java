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
import com.fitnessforge.entity.MemberDTO;
import com.fitnessforge.service.MemberService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

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

  @GetMapping("/{id}")
  public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
    Member savedMember = memberService.getMember(id);
    MemberDTO responseToSend = new MemberDTO();

    responseToSend.setId(savedMember.getId());
    responseToSend.setName(savedMember.getName());
    responseToSend.setAge(savedMember.getAge());
    responseToSend.setGender(savedMember.getGender());
    responseToSend.setFitnessLevel(savedMember.getFitnessLevel());
    responseToSend.setWeight(savedMember.getWeight());
    responseToSend.setHeight(savedMember.getHeight());
    responseToSend.setEmail(savedMember.getEmail());
    responseToSend.setJoinedOn(savedMember.getJoinedOn());

    return new ResponseEntity<>(responseToSend, HttpStatus.OK);
  }

  @GetMapping("/auth_refresh/{id}")
  public ResponseEntity<String> getRefreshToken(@PathVariable Long id) {
    return new ResponseEntity<>(memberService.getRefreshToken(id), HttpStatus.OK);
  }
}
