package com.goldys.enquiryservice.commander;

import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CachingImplementationTest {

    @Test
    public void testCacheOnListAllEnquiries() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.enquiryservice.service.EnquiryServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("listAllEnquiries")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "listAllEnquiries() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnGetEnquiryByCode() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.enquiryservice.service.EnquiryServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("getEnquiryByCode")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "getEnquiryByCode() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnAddNewEnquiry() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.enquiryservice.service.EnquiryServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("addNewEnquiry")) {
                if ((method.getAnnotation(CacheEvict.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "addNewEnquiry() method should have @CacheEvict Annotation");
        assertThat(counter).isGreaterThan(0);

    }

}
