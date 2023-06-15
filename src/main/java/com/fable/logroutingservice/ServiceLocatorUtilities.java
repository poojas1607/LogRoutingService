package com.fable.logroutingservice;

import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;

@UtilityClass
public class ServiceLocatorUtilities {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext currentApplicationContext) {
        applicationContext = currentApplicationContext;
    }

    public <T> T getBean(Class<T> aClass) {
        return applicationContext.getBean(aClass);
    }
}