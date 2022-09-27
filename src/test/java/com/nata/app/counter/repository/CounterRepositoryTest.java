package com.nata.app.counter.repository;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.exception.CounterNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CounterRepositoryTest {

    @Autowired
    private CounterRepository counterRepository;

    @Test
    void findByName() {
        counterRepository.save(new Counter("name", new AtomicInteger(0)));
        Counter counter = counterRepository.findByName("name");
        assertThat(counter).hasNoNullFieldsOrProperties();
    }

    @Test
    void increment() {
        counterRepository.save(new Counter("name", new AtomicInteger(0)));
        Counter counter = counterRepository.increment("name");
        assertThat(counter).hasNoNullFieldsOrPropertiesExcept()
                .extracting(Counter::getCount).extracting(AtomicInteger::get).isEqualTo(1);
    }

    @Test
    void delete() {
        counterRepository.save(new Counter("name", new AtomicInteger(0)));
        counterRepository.delete("name");
        assertThatThrownBy(() -> counterRepository.findByName("name"))
                .isInstanceOf(CounterNotFoundException.class);
    }

    @Test
    void getNames() {
        counterRepository.save(new Counter("name", new AtomicInteger(0)));
        Set<String> names = counterRepository.getNames();
        assertThat(names).isNotEmpty().first().isEqualTo("name");
    }

    @Test
    void getSum() {
        counterRepository.save(new Counter("name", new AtomicInteger(1)));
        counterRepository.save(new Counter("name2", new AtomicInteger(2)));
        Integer sum = counterRepository.getSum();
        assertThat(sum).isEqualTo(3);
    }
}