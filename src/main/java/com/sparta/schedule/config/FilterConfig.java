package com.sparta.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sparta.schedule.jwt.JwtFilter;
import com.sparta.schedule.jwt.JwtService;

@Configuration
public class FilterConfig {

	private final JwtService jwtService;

	@Autowired
	public FilterConfig(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter(jwtService));
		registrationBean.addUrlPatterns("/api/schedules/*", "/api/comments/*");
		return registrationBean;
	}
}
