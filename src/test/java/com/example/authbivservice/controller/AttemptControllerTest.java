package com.example.authbivservice.controller;

import com.example.authbivservice.domen.Status;
import com.example.authbivservice.domen.dto.ResponseStatusDto;
import com.example.authbivservice.service.impl.AttemptServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        AttemptController.class,

        MockMvcAutoConfiguration.class
})
@AutoConfigureMockMvc
class AttemptControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockBean
    AttemptServiceImpl attemptService;

    @Test
    void lastTry_shouldReturnResponseStatusDto() throws Exception {

        String code = "123456";
        LocalDateTime localDateTime = LocalDateTime.now();

        ResponseStatusDto responseStatusDto = new ResponseStatusDto(Status.SUCCESS, localDateTime);


        when(attemptService.lastTry(code, Status.SUCCESS)).thenReturn(responseStatusDto);

        mockMvc
                .perform(
                        get("/attempts/" + code)
                                .param("status", String.valueOf(Status.SUCCESS))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));

    }
}