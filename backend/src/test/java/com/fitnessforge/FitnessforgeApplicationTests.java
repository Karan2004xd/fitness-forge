package com.fitnessforge;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FitnessforgeApplicationTests {

  @Autowired
  private MockMvc mockMvc;

	@Test
	void contextLoads() {
    assertNotNull(mockMvc);
	}

}
