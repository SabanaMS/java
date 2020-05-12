package com.goldys.enquiryservice.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.enquiryservice.EnquiryServiceApplication;
import com.goldys.enquiryservice.model.Enquiry;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EnquiryServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnquiryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Enquiry enquiry;

    private final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb21zdWJocmFAZ21haWwuY29tIiwicm9sZSI6ImFkbWluIiwiaWF0IjoxNTg4MDk2NzM4fQ.79zL5FglhKiCOQpsMVeyvCrMWpXMeq7YNm7-zp4krU8";


    @BeforeEach
    public void setUp() {

        enquiry = new Enquiry("Rajesh", "rajesh@abc.com", "9810098100", "New Subscription");
        enquiry.setEnquiryCode("1");

    }

    @Order(2)
    @Test
    public void getAllEnquiriesSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/enquiryservice/admin/")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(3)
    @Test
    public void getEnquiryByCodeSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/enquiryservice/admin/1")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(4)
    @Test
    public void getEnquiryByCodeFailure() throws Exception {

        mockMvc.perform(get("/api/v1/enquiryservice/admin/2")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Order(1)
    @Test
    public void addEnquirySuccess() throws Exception {

        mockMvc.perform(post("/api/v1/enquiryservice/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(enquiry)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
