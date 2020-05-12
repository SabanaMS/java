package com.goldys.enquiryservice.test.model;

import com.goldys.enquiryservice.model.Enquiry;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class EnquiryTest {

    /*
     * Test whether bean instance can be created successfully by calling the constructor
     */
    @Test
    public void BeanTest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Enquiry.class);
    }
}
