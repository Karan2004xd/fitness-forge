package com.fitnessforge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fitnessforge.service.ExerciseService;

/** 
 * <b>Description:</b>
 * <p>
 *  Main application entry point
 * </p>
 * */
@SpringBootApplication
public class FitnessforgeApplication implements CommandLineRunner {

  @Autowired
  private ExerciseService exerciseService;

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

  /** 
   * Implemented method of the interface org.springframework.boot.CommandLineRunner,
   * which runs the method to populate the database with exercises if doesn't exists yet.
   *
   * @param args String arguments
   * @throws Exception thrown in case of an internal exception
   * */
  @Override
  public void run(String... args) throws Exception {
    exerciseService.populateDataIfNeeded();
  }

}
