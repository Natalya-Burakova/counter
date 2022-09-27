package com.nata.app.counter.mapper;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.dto.CounterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.concurrent.atomic.AtomicInteger;

@Mapper
public interface CounterMapper {

    Counter toCounter(String name, AtomicInteger count);

    @Mapping(target = "count", expression = "java(counter.getCount().get())")
    CounterDto toCounterDto(Counter counter);
}


