package com.fitnessforge.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * <b>Description:</b>
 * <p>
 *  This is a configuration class for org.springframework.web.client.RestTemplate,
 * </p>
 * */
@Configuration
public class RestTemplateConfig {

  /** 
   * Empty default constructor
   * */
  public RestTemplateConfig() {}

  /** 
   * Basically the method configures the RestTemplate object to accept media type of
   * application/json and text/plain.
   *
   * @return an object of org.springframework.web.client.RestTemplate
   * */
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
    messageConverters.add(converter);
    return restTemplate;
  }
}
