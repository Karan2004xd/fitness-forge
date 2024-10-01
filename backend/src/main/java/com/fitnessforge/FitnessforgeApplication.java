package com.fitnessforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/** 
 * <b>Description:</b>
 * <p>
 *  Main application entry point
 * </p>
 * */
@SpringBootApplication
public class FitnessforgeApplication {

  /** 
   * Empty default constructor
   * */
  public FitnessforgeApplication() {}

  /** 
   * Registers the BCryptPasswordEncoder class as a bean and generates a new object
   *
   * @return an object of org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
   * */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /** 
   * Main entry point method of the application
   *
   * @param args command line arguments
   * */
	public static void main(String[] args) {
		SpringApplication.run(FitnessforgeApplication.class, args);
	}

}
