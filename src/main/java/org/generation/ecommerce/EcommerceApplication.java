package org.generation.ecommerce;

import org.generation.ecommerce.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }//main

    //? Componente a ejecutar esta aplicacion, y por el tipo de metodo detectará que es un filtro ⬇️
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns("/api/productos/*");
        registrationBean.addUrlPatterns("/api/usuarios/*");
        return registrationBean;
    }

}//class EcommerceApplication
