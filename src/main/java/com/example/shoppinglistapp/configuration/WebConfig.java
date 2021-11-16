package com.example.shoppinglistapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//    }

    @Bean
    public ITemplateResolver viewResolver() {
        ClassLoaderTemplateResolver bean = new ClassLoaderTemplateResolver();

        bean.setPrefix("templates/");
        bean.setCacheable(false);
        bean.setSuffix(".html");
        bean.setTemplateMode(TemplateMode.HTML);
        bean.setCharacterEncoding("UTF-8");

        return bean;
    }
}
