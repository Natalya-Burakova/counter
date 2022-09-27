package com.nata.app.counter.mapper;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.dto.CounterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CounterMapperTest {

    @Autowired
    private CounterMapper counterMapper;

    @Test
    void toCounter() {
        Counter counter = counterMapper.toCounter("name", new AtomicInteger(0));
        assertThat(counter).hasNoNullFieldsOrProperties();
    }

    @Test
    void toCounter_withNull_ShouldReturnNull() {
        Counter counter = counterMapper.toCounter(null, null);
        assertThat(counter).isNull();
    }

    @Test
    void toCounterDto() {
        CounterDto counterDto = counterMapper.toCounterDto(new Counter("name", new AtomicInteger(0)));
        assertThat(counterDto).hasNoNullFieldsOrProperties();
    }

    @Test
    void toCounterDto_withNull_ShouldReturnNull() {
        CounterDto counterDto = counterMapper.toCounterDto(null);
        assertThat(counterDto).isNull();
    }
}