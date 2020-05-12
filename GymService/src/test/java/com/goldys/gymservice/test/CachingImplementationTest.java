package com.goldys.gymservice.test;

import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CachingImplementationTest {

    @Test
    public void testCacheOnListAllPrograms() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.service.ProgramServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("listAllPrograms")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "listAllPrograms() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnGetProgramByCode() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.service.ProgramServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("getProgramByCode")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "getProgramByCode() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnAddProgram() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.service.ProgramServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("addNewProgram")) {
                if ((method.getAnnotation(CacheEvict.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "addNewProgram() method should have @CacheEvict Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnUpdateProgram() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.service.ProgramServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("updateExistingProgram")) {
                if ((method.getAnnotation(CacheEvict.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "updateExistingProgram() method should have @CacheEvict Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnListAllActivePrograms() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.service.ProgramServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("listAllActivePrograms")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "listAllActivePrograms() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

}
