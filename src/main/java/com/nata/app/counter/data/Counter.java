package com.nata.app.counter.data;

import lombok.*;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Counter {
    private String name;
    private AtomicInteger count;

    public void increment() {
       this.count.incrementAndGet();
    }
}
