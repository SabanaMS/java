package com.goldys.gymservice.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class ProgramTest {

    @Test
    public void BeanTest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Program.class);
    }
}
