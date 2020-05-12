package com.goldys.gymservice.commander.model;

import com.goldys.gymservice.model.Program;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class ProgramTest {

    /*
     * Test whether bean instance can be created successfully by calling the constructor
     */
    @Test
    public void BeanTest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Program.class);
    }
}
