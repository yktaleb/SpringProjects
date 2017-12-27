package com.hw.repository;

import com.hw.ioc.Benchmark;

public interface RepoBean {
    void init();

    @Benchmark
    double calculate();
}
