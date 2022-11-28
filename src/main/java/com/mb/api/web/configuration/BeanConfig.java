package com.mb.api.web.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
