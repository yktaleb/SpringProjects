package com.hw.repository;

import com.hw.ioc.Benchmark;

public class RepoBean implements RepoBeanInterFace {

    public RepoBean() {
        System.out.println(this.getClass().getSimpleName() + " is created");
    }

    @Override
    public void init(){
        System.out.println(this.getClass().getSimpleName() + " called init");
    }

    @Override
    @Benchmark
    public double calculate(){
        double sum = 0;
        for (int i = 0; i < 100; i++) {
            double x = sum;
            sum += Math.sin(x)*Math.sin(x) + Math.cos(x)*Math.cos(x);
        }
        return sum;
    }
}
