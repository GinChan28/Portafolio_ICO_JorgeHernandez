package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration

public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {

        var sir = new SessionLocaleResolver();
        sir.setDefaultLocale(Locale.getDefault());
        sir.setLocaleAttributeName("session.current.locale");
        sir.setTimeZoneAttributeName("session.current.timezone");
        return sir;

    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        var ici = new LocaleChangeInterceptor();
        ici.setParamName("lang");

        return ici;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {

        registro.addInterceptor(localeChangeInterceptor());

    }
    
    @Bean("messageSource")
    public MessageSource messageSource(){
    
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    
    messageSource.setBasename("messages");
    
    messageSource.setDefaultEncoding("UTF-8");
    
    return messageSource;
    
    }
}
