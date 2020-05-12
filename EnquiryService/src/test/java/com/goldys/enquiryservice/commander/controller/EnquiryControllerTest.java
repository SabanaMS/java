package com.goldys.enquiryservice.commander.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.enquiryservice.controller.EnquiryController;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.service.EnquiryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnquiryControllerTest {

    private MockMvc mockMvc;
    private Enquiry enquiry;
    private List<Enquiry> enquiryList;

    @Mock
    EnquiryService enquiryService;
    @InjectMocks
    EnquiryController enquiryController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(enquiryController).build();
        enquiryList = new ArrayList<>();
        enquiry = new Enquiry("Rajesh", "rajesh@abc.com", "9810098100", "New Subscription");
        enquiry.setEnquiryCode("1");
        enquiryList.add(enquiry);

    }

    @Test
    public void getAllEnquiriesSuccess() throws Exception {

        when(enquiryService.listAllEnquiries()).thenReturn(enquiryList);
        mockMvc.perform(get("/api/v1/enquiryservice/admin/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getEnquiryByCodeSuccess() throws Exception {

        when(enquiryService.getEnquiryByCode("1")).thenReturn(enquiry);
        mockMvc.perform(get("/api/v1/enquiryservice/admin/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getEnquiryByCodeFailure() throws Exception {

        when(enquiryService.getEnquiryByCode("2")).thenReturn(null);
        mockMvc.perform(get("/api/v1/enquiryservice/admin/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void addEnquirySuccess() throws Exception {

        when(enquiryService.addNewEnquiry(any())).thenReturn(enquiry);
        mockMvc.perform(post("/api/v1/enquiryservice/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(enquiry)))
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

