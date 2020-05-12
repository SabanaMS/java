package com.goldys.ticketservice.commander;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCoverageFinder {

    @Test
    public void testModelTestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.commander.model.TicketTest")
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
        Method[] methods = Class.forName("com.goldys.ticketservice.commander.service.TicketServiceTest")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(3);
    }

    @Test
    public void testControllerTestCaseCoverage() throws ClassNotFoundException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.commander.controller.TicketControllerTest")
                .getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getAnnotation(Test.class)) != null) {
                flag = true;
                counter++;
            }
        }
        assertTrue(flag);
        assertThat(counter).isGreaterThan(3);
    }

}
