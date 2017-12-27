package com.hw.repository;

import com.hw.ioc.Benchmark;

public interface RepoBeanInterFace {
    void init();

    @Benchmark
    double calculate();
}
