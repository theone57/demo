package com.all.lin.statrter.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ValidationConfig {

    @Bean
    public Validator getValidator() {

        return Validation.byDefaultProvider().
        configure().messageInterpolator(
                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("i18n/validation/message"))).
        buildValidatorFactory().getValidator();

    }

}