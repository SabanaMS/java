package com.goldys.ticketservice.commander;

import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CachingImplementationTest {

    @Test
    public void testCacheOnListAllTickets() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.service.TicketServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("listAllTickets")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "listAllTickets() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnListAllOpenTickets() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.service.TicketServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("listAllOpenTickets")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "listAllOpenTickets() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnGetTicketByTicketId() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.service.TicketServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("getTicketByTicketId")) {
                if ((method.getAnnotation(Cacheable.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "getTicketByTicketId() method should have @Cacheable Annotation");
        assertThat(counter).isGreaterThan(0);

    }

    @Test
    public void testCacheOnUpdateTicket() throws ClassNotFoundException, NoSuchMethodException {

        boolean flag = false;
        int counter = 0;
        Method[] methods = Class.forName("com.goldys.ticketservice.service.TicketServiceImpl")
                .getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("updateTicket")) {
                if ((method.getAnnotation(CacheEvict.class)) != null) {
                    flag = true;
                    counter++;
                }
            }
        }
        assertTrue(flag, "updateTicket() method should have @CacheEvict Annotation");
        assertThat(counter).isGreaterThan(0);

    }

}
