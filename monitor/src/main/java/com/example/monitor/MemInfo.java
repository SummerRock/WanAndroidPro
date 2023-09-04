package com.example.monitor;

public class MemInfo {

    private final long totalMemory;

    public MemInfo(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }
}
