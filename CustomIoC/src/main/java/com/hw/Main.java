package com.hw;

import com.hw.ioc.Config;
import com.hw.ioc.JavaConfig;
import com.hw.ioc.SimpleIoC;
import com.hw.repository.RepoBean;
import com.hw.repository.RepoBeanInterFace;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Class<?>> beanDescriptions = new HashMap<String, Class<?>>(){{
            put("repoBean", RepoBean.class);
        }};

        Config config = new JavaConfig(beanDescriptions);
//        "repoBean",
//        RepoBean.class
        SimpleIoC ioC = new SimpleIoC(config);
        RepoBeanInterFace repoBean = (RepoBeanInterFace) ioC.getBean("repoBean");
        repoBean.calculate();
    }
}
