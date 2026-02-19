package com.automation.framework.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.automation.framework.utils.RetryAnalyzer;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {

        Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();

     // If no retry analyzer already assigned → attach ours
        if (retry == null || retry.getName().contains("DisabledRetryAnalyzer")) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
