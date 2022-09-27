package com.nata.app.counter.service;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.mapper.CounterMapper;
import com.nata.app.counter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;
    private final CounterMapper counterMapper;

    public Counter getByName(String name) {
        log.info("Getting counter by name: {}", name);
        return counterRepository.findByName(name);
    }

    public Counter createCounter(String name) {
        log.info("Creating counter for name: {}", name);
        Counter counter = counterMapper.toCounter(name, new AtomicInteger(0));
        counterRepository.save(counter);
        return counter;
    }

    public Counter incrementByName(String name) {
        log.info("Incrementing counter for name: {}", name);
        return counterRepository.increment(name);
    }

    public void deleteByName(String name) {
        log.info("Deleting counter for name: {}", name);
        counterRepository.delete(name);
    }

    public int getCountersSum() {
        log.info("Getting sum for all counters");
        return counterRepository.getSum();
    }

    public Set<String> getCountersNames() {
        log.info("Getting all counters names");
        return counterRepository.getNames();
    }
}
