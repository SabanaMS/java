package com.goldys.gymservice.test;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCoverageFinder {

    @Test
    public void testModelTestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.commander.model.ProgramTest")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testServiceTestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.commander.service.ProgramServiceTest")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(8);
    }

    @Test
    public void testControllerV1TestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.commander.controller.v1.ProgramControllerV1Test")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(6);
    }

    @Test
    public void testControllerV2TestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.gymservice.commander.controller.v2.ProgramControllerV2Test")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(0);
    }
}
