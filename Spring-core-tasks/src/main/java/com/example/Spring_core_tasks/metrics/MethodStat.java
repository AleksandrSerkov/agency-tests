package com.example.Spring_core_tasks.metrics;

public class MethodStat {
    private final String method;
    private final long count;
    private final long min, avg, max;

    public MethodStat(String method, long count, long min, long avg, long max) {
        this.method = method;
        this.count = count;
        this.min = min;
        this.avg = avg;
        this.max = max;
    }

    public String getMethod() { return method; }
    public long getCount() { return count; }
    public long getMin() { return min; }
    public long getAvg() { return avg; }
    public long getMax() { return max; }
}

