package org.com.singlefile.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;


import java.util.List;
import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/ValidationMessages", "classpath:i18n/CustomValidationMessages");
        messageSource.setDefaultLocale(Locale.US);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    //TODO: No esta tomando el Local  Default.
   @Bean
    public LocaleContextResolver localeResolver() {
        var localeResolver = new AcceptHeaderLocaleContextResolver();
       System.out.println(localeResolver.getDefaultLocale());
        localeResolver.setDefaultLocale(Locale.US);
       System.out.println(localeResolver.getDefaultLocale());

       localeResolver.setSupportedLocales(List.of(Locale.US, Locale.CHINESE));
        return localeResolver;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

}
