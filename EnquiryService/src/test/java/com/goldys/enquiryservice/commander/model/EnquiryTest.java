package com.goldys.enquiryservice.commander.model;

import com.goldys.enquiryservice.model.Enquiry;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class EnquiryTest {

    @Test
    public void BeanTest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Enquiry.class);
    }
}
