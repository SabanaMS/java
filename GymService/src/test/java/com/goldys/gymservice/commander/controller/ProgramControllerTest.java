package com.goldys.gymservice.commander.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.gymservice.controller.ProgramController;
import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProgramControllerTest {

    private MockMvc mockMvc;
    private Program program;
    private List<Program> programList;

    @Mock
    ProgramService programService;
    @InjectMocks
    ProgramController programController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(programController).build();
        programList = new ArrayList<>();
        program = new Program("All Access(1 Month)","Access to all equipments with general trainers", 1, 1500, 0.1f, true);
        program.setProgramCode("1");
        programList.add(program);

    }

    @Test
    public void getAllProgramsSuccess() throws Exception {

        when(programService.listAllPrograms()).thenReturn(programList);
        mockMvc.perform(get("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllActiveProgramsSuccess() throws Exception {

        when(programService.listAllActivePrograms()).thenReturn(programList);
        mockMvc.perform(get("/api/v1/gymservice/showAllActive").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByProgramNameSuccess() throws Exception {

        when(programService.getProgramByCode("1")).thenReturn(program);
        mockMvc.perform(get("/api/v1/gymservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByRecipeNameFailure() throws Exception {

        when(programService.getProgramByCode("2")).thenReturn(null);
        mockMvc.perform(get("/api/v1/gymservice/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByProgramDurationSuccess() throws Exception {

        when(programService.getProgramByDuration(1)).thenReturn(programList);
        mockMvc.perform(get("/api/v2/gymservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addProgramSuccess() throws Exception {

        when(programService.addNewProgram(any())).thenReturn(program);
        mockMvc.perform(post("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void addProgramFailure() throws Exception {

        when(programService.addNewProgram(any())).thenThrow(ProgramAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateProgramSuccess() throws Exception {

        when(programService.updateExistingProgram(any())).thenReturn(program);
        mockMvc.perform(put("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateProgramFailure() throws Exception {

        when(programService.updateExistingProgram(any())).thenThrow(ProgramNotFoundException.class);
        mockMvc.perform(put("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program  )))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
