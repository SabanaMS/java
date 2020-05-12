package com.goldys.enquiryservice.commander.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.repository.EnquiryRepository;
import com.goldys.enquiryservice.service.EnquiryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EnquiryServiceTest {

    @Mock
    private EnquiryRepository enquiryRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private EnquiryServiceImpl enquiryService;

    private Enquiry enquiry;

    private List<Enquiry> enquiryList;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        enquiryList = new ArrayList<>();
        enquiry = new Enquiry("John Doe", "john@abc.com", "9810098100", "New Subscription");
        enquiry.setEnquiryCode("1");
        enquiryList.add(enquiry);

    }

    @AfterEach
    public void tearDown() throws Exception {
        enquiry = null;
    }

    @Test
    @Rollback(true)
    public void testAddNewEnquirySuccess() {

        when(enquiryRepository.save(any())).thenReturn(enquiry);

        assertEquals(enquiry, enquiryService.addNewEnquiry(enquiry));

        verify(enquiryRepository, times(1)).save(any());

    }


    @Test
    @Rollback(true)
    public void testGetAllEnquiriesSuccess() {

        when(enquiryRepository.findAll()).thenReturn(enquiryList);

        assertEquals(enquiryList, enquiryService.listAllEnquiries());

        verify(enquiryRepository, times(1)).findAll();

    }

    @Test
    @Rollback(true)
    public void testGetEnquiryByCodeSuccess() throws EnquiryNotFoundException {

        when(enquiryRepository.findById(any())).thenReturn(Optional.of(enquiry));

        assertEquals(enquiry, enquiryService.getEnquiryByCode(enquiry.getEnquiryCode()));

        verify(enquiryRepository, times(2)).findById(any());

    }

    @Test
    @Rollback(true)
    public void testGetEnquiryByCodeFailure() throws EnquiryNotFoundException {

        when(enquiryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EnquiryNotFoundException.class, () -> enquiryService.getEnquiryByCode(enquiry.getEnquiryCode()));

        verify(enquiryRepository, times(1)).findById(any());

    }
}
