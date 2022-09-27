package com.nata.app.counter.service;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.repository.CounterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @MockBean
    private CounterRepository counterRepository;

    @Test
    void getByName() {
        Counter counter = new Counter("name", new AtomicInteger(0));
        doReturn(counter).when(counterRepository).findByName("name");
        Counter result = counterService.getByName("name");
        assertThat(result).isEqualTo(result);
    }

    @Test
    void createCounter() {
        doNothing().when(counterRepository).save(any());
        Counter result = counterService.createCounter("name");
        assertThat(result).hasNoNullFieldsOrProperties();
    }

    @Test
    void incrementByName() {
        Counter counter = new Counter("name", new AtomicInteger(0));
        doReturn(counter).when(counterRepository).increment("name");
        Counter result = counterService.incrementByName("name");
        assertThat(result).isEqualTo(counter);
    }

    @Test
    void deleteByName() {
        doNothing().when(counterRepository).delete("name");
        counterService.deleteByName("name");
        verify(counterRepository).delete("name");
    }

    @Test
    void getSum() {
        doReturn(2).when(counterRepository).getSum();
        Integer result = counterService.getCountersSum();
        assertThat(result).isEqualTo(2);
    }

    @Test
    void getNames() {
        doReturn(Set.of("name")).when(counterRepository).getNames();
        Set<String> result = counterService.getCountersNames();
        assertThat(result).isNotEmpty().first().isEqualTo("name");
    }
}