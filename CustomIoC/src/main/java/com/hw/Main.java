package com.hw;

import com.hw.ioc.Config;
import com.hw.ioc.JavaConfig;
import com.hw.ioc.SimpleIoC;
import com.hw.repository.RepoBeanImpl;
import com.hw.repository.RepoBean;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Class<?>> beanDescriptions = new HashMap<String, Class<?>>(){{
            put("repoBean", RepoBeanImpl.class);
        }};

        Config config = new JavaConfig(beanDescriptions);
        SimpleIoC ioC = new SimpleIoC(config);

        RepoBean repoBean = (RepoBean) ioC.getBean("repoBean");
        System.out.println(repoBean.calculate());
    }
}
