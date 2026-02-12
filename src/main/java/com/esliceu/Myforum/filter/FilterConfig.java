package com.esliceu.Myforum.filter;

import com.esliceu.Myforum.service.JWTService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public JwtFilter jwtFilter(JWTService jwtService) {
        return new JwtFilter(jwtService);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {

        FilterRegistrationBean<JwtFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
