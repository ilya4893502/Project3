package com.spring.project;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@SpringBootApplication
public class ProjectApplication {

    private int maxUploadSizeInMb = 10 * 1024 * 1024;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }



    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
