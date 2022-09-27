package com.nata.app.counter.rest;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.service.CounterService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterService counterService;

    @Test
    @SneakyThrows
    void getByName() {
        doReturn(new Counter("name", new AtomicInteger(0))).when(counterService).getByName("name");
        mockMvc.perform(get("/api/v1/counters/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"name\",\"count\":0}"));
    }

    @Test
    @SneakyThrows
    void create() {
        doReturn(new Counter("name", new AtomicInteger(0))).when(counterService).createCounter("name");
        mockMvc.perform(post("/api/v1/counters")
                        .content("name"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"name\",\"count\":0}"));
    }

    @Test
    @SneakyThrows
    void increment() {
        doReturn(new Counter("name", new AtomicInteger(1))).when(counterService).incrementByName("name");
        mockMvc.perform(put("/api/v1/counters/increment/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"name\",\"count\":1}"));
    }

    @Test
    @SneakyThrows
    void deleteByName() {
        doNothing().when(counterService).deleteByName("name");
        mockMvc.perform(delete("/api/v1/counters/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void sum() {
        doReturn(3).when(counterService).getCountersSum();
        mockMvc.perform(get("/api/v1/counters/sum")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("3"));
    }

    @Test
    @SneakyThrows
    void names() {
        doReturn(Set.of("name")).when(counterService).getCountersNames();
        mockMvc.perform(get("/api/v1/counters/names")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"name\"]"));
    }
}