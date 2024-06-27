package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	
	 @Bean
	    public FilterRegistrationBean<LoginFilter> loggingFilter() {
	        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

	        registrationBean.setFilter(new LoginFilter());
	        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs

	        return registrationBean;
	    }
}
