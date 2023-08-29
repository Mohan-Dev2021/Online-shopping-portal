package com.online.shop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfi {

	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }
}
