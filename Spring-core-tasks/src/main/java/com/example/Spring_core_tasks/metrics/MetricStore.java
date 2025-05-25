package com.example.Spring_core_tasks.metrics;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MetricStore {
  private final List<Record> recs = Collections.synchronizedList(new ArrayList<>());

  public void record(String m, long t) {
    recs.add(new Record(m, LocalDateTime.now(), t));
  }

  public List<MethodStat> total(LocalDateTime from, LocalDateTime to) {
    return recs.stream()
      .filter(r -> !r.ts.isBefore(from) && !r.ts.isAfter(to))
      .collect(Collectors.groupingBy(r->r.method))
      .entrySet().stream()
      .map(e->{
        long[] a = e.getValue().stream().mapToLong(x->x.time).toArray();
        return new MethodStat(e.getKey(), a.length,
                              Arrays.stream(a).min().orElse(0),
                              (long)Arrays.stream(a).average().orElse(0),
                              Arrays.stream(a).max().orElse(0));
      }).toList();
  }

  static class Record { String method; LocalDateTime ts; long time;
    Record(String m, LocalDateTime t, long tm){method=m;ts=t;time=tm;}
  }
}
