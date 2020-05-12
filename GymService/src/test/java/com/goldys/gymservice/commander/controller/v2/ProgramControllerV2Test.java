package com.goldys.gymservice.commander.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.gymservice.controller.v2.ProgramControllerV2;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.service.ProgramService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProgramControllerV2Test {

    private MockMvc mockMvc;
    private Program program;
    private List<Program> programList;

    @Mock
    ProgramService programService;
    @InjectMocks
    ProgramControllerV2 programControllerV2;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(programControllerV2).build();
        programList = new ArrayList<>();
        program = new Program("All Access(1 Month)", "Access to all equipments with general trainers", 1, 1500, 0.1f, true);
        program.setProgramCode("1");
        programList.add(program);

    }

    @Test
    public void getAllActiveProgramsSuccess() throws Exception {

        when(programService.listAllActivePrograms()).thenReturn(programList);
        mockMvc.perform(get("/api/v2/gymservice/showAllActive").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getProgramByDurationSuccess() throws Exception {

        when(programService.getProgramByDuration(1)).thenReturn(programList);
        mockMvc.perform(get("/api/v2/gymservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
