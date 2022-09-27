package com.nata.app.counter.repository;

import com.nata.app.counter.data.Counter;
import com.nata.app.counter.exception.CounterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CounterRepository {

    public final ConcurrentMapCache cache;

    public Counter findByName(String name) {
        return Optional.ofNullable(cache.get(name, Counter.class))
                .orElseThrow(CounterNotFoundException::new);
    }

    public void save(Counter counter) {
        cache.put(counter.getName(), counter);
    }

    public Counter increment(String name) {
        Counter counter = findByName(name);
        counter.increment();
        return counter;
    }

    public void delete(String name) {
        cache.evictIfPresent(name);
    }

    public int getSum() {
        return cache.getNativeCache().keySet().stream()
                .map(key -> findByName((String) key).getCount().get())
                .reduce(0, Integer::sum);
    }

    public Set<String> getNames() {
        return cache.getNativeCache().keySet().stream()
                .map(key -> (String) key)
                .collect(Collectors.toSet());
    }
}
