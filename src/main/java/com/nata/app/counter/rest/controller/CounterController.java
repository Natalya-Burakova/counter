package com.nata.app.counter.rest.controller;

import com.nata.app.counter.dto.CounterDto;
import com.nata.app.counter.mapper.CounterMapper;
import com.nata.app.counter.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/counters")
public class CounterController {

    private final CounterService counterService;
    private final CounterMapper counterMapper;

    @GetMapping("/{name}")
    public CounterDto getByName(@PathVariable String name) {
        return counterMapper.toCounterDto(counterService.getByName(name));
    }

    @PostMapping
    public CounterDto create(@RequestBody String name) {
        return counterMapper.toCounterDto(counterService.createCounter(name));
    }

    @PutMapping("/increment/{name}")
    public CounterDto increment(@PathVariable String name) {
        return counterMapper.toCounterDto(counterService.incrementByName(name));
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        counterService.deleteByName(name);
    }

    @GetMapping("/sum")
    public int sum() {
        return counterService.getCountersSum();
    }

    @GetMapping("/names")
    public Set<String> names() {
        return counterService.getCountersNames();
    }
}
