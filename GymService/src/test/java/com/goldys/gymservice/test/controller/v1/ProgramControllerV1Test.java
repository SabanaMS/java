package com.goldys.gymservice.test.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.gymservice.controller.v1.ProgramControllerV1;
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

public class ProgramControllerV1Test {

    private MockMvc mockMvc;
    private Program program;
    private List<Program> programList;

    /* ProgramService should be mocked and be injected to ProgramControllerV1 */

    @Mock
    ProgramService programService;
    @InjectMocks
    ProgramControllerV1 programControllerV1;

    /*  Create an object of the Program class and and list of program objects which can be
        used for mocking various service methods. */
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(programControllerV1).build();
        programList = new ArrayList<>();
        program = new Program("All Access(1 Month)", "Access to all equipments with general trainers", 1, 1500, 0.1f, true);
        program.setProgramCode("1");
        programList.add(program);

    }

    /* when GET request is sent to "/api/v1/gymservice", the status returned should be ok and the list of
      programs should be returned. */
    @Test
    public void getAllProgramsSuccess() throws Exception {

        when(programService.listAllPrograms()).thenReturn(programList);
        mockMvc.perform(get("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    /* when GET request is sent to "/api/v1/gymservice/1", the status returned should be ok and the program
     with programCode 1 should be returned. */
    @Test
    public void getProgramByCodeSuccess() throws Exception {

        when(programService.getProgramByCode("1")).thenReturn(program);
        mockMvc.perform(get("/api/v1/gymservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /* when GET request is sent to "/api/v1/gymservice/2", the status returned should
    be ok and no result should be returned. */
    @Test
    public void getProgramByCodeFailure() throws Exception {

        when(programService.getProgramByCode("2")).thenReturn(null);
        mockMvc.perform(get("/api/v1/gymservice/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    /* when POST request is sent to "/api/v1/gymservice/" with Program JSON, the status returned should be "created" and
        the newly created program should be returned */
    @Test
    public void addProgramSuccess() throws Exception {

        when(programService.addNewProgram(any())).thenReturn(program);
        mockMvc.perform(post("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    /* when POST request is sent to "/api/v1/gymservice/" with Program JSON having duplicate programCode, the status
    returned should be "conflict" and ProgramAlreadyExistsException should be thrown */
    @Test
    public void addProgramFailure() throws Exception {

        when(programService.addNewProgram(any())).thenThrow(ProgramAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }

    /* when PUT request is sent to "/api/v1/gymservice/" with Program JSON, the status returned should be "ok" and
  the updated program should be returned */
    @Test
    public void updateProgramSuccess() throws Exception {

        when(programService.updateExistingProgram(any())).thenReturn(program);
        mockMvc.perform(put("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    /* when PUT request is sent to "/api/v1/gymservice/" with Program JSON, the status returned should be "not found" and
   ProgramNotFoundException should be thrown */
    @Test
    public void updateProgramFailure() throws Exception {

        when(programService.updateExistingProgram(any())).thenThrow(ProgramNotFoundException.class);
        mockMvc.perform(put("/api/v1/gymservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
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
