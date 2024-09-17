package com.example.andreas.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.andreas.model.Container;

@Configuration
@ComponentScan(basePackages="com.example.andreas")
public class ContainerBeans {

	@Bean
	@Primary
	public Container getContainer100() {
		return new Container(100.0);
	}
}
