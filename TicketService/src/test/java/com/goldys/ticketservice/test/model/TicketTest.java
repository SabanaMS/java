package com.goldys.ticketservice.test.model;

import com.goldys.ticketservice.model.Ticket;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class TicketTest {

    @Test
    public void BeanTest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Ticket.class);
    }
}
