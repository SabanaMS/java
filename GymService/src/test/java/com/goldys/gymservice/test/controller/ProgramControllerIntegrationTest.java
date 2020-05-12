package com.goldys.gymservice.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.gymservice.GymServiceApplication;
import com.goldys.gymservice.model.Program;
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
        classes = GymServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProgramControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Program program;

    private final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb21zdWJocmFAZ21haWwuY29tIiwicm9sZSI6ImFkbWluIiwiaWF0IjoxNTg4MDk2NzM4fQ.79zL5FglhKiCOQpsMVeyvCrMWpXMeq7YNm7-zp4krU8";


    @BeforeEach
    public void setUp() {


        program = new Program("All Access(1 Month)", "Access to all equipments with general trainers", 1, 1500, 0.1f, true);
        program.setProgramCode("p001");


    }

    @Order(3)
    @Test
    public void getAllProgramsSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(get("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }


    @Order(4)
    @Test
    public void getProgramByCodeSuccess() throws Exception {


        mockMvc.perform(get("/api/v1/gymservice/p001")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(5)
    @Test
    public void getProgramByCodeFailure() throws Exception {


        mockMvc.perform(get("/api/v1/gymservice/p002")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Order(1)
    @Test
    public void addProgramSuccess() throws Exception {


        mockMvc.perform(post("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Order(2)
    @Test
    public void addProgramFailure() throws Exception {


        mockMvc.perform(post("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }

    @Order(6)
    @Test
    public void updateProgramSuccess() throws Exception {


        mockMvc.perform(put("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Order(7)
    @Test
    public void updateProgramFailure() throws Exception {

        program.setProgramCode("p002");
        mockMvc.perform(put("/api/v1/gymservice")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }

    @Order(8)
    @Test
    public void getAllActiveProgramsSuccess() throws Exception {

        mockMvc.perform(get("/api/v2/gymservice/showAllActive").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(9)
    @Test
    public void getProgramByDurationSuccess() throws Exception {

        mockMvc.perform(get("/api/v2/gymservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(10)
    @Test
    public void deleteProgramSuccess() throws Exception {

        mockMvc.perform(delete("/api/v1/gymservice/p001")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Order(11)
    @Test
    public void deleteProgramFailure() throws Exception {
        mockMvc.perform(delete("/api/v1/gymservice/p002")
                .header("Authorization", "Bearer " + JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(program)))
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
