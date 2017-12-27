package com.hw;

import com.hw.ioc.BeanDefinition;
import com.hw.ioc.Config;
import com.hw.ioc.SimpleIoC;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleIoCTest {

    protected Config config = new Config() {
        @Override
        public List<String> beanNames() {
            return Collections.emptyList();
        }

        @Override
        public BeanDefinition beanDefinition(String beanName) {
            return null;
        }
    };

    @Test
    public void createIoC() {
        new SimpleIoC(config);
    }

    @Test
    public void beanDefShouldBeEmpty() {
        SimpleIoC simpleIoC = new SimpleIoC(config);
        List<String> beanDefinitions = simpleIoC.beanDefinitions();
        assertEquals(Collections.emptyList(), beanDefinitions);
    }

    @Test
    public void beanDefWithOneBeanInConfig() {
        String beanName = "bean1";

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {
                return null;
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        List<String> beanDefinitions = simpleIoC.beanDefinitions();

        assertEquals(Arrays.asList(beanName), beanDefinitions);
    }

    @Test
    public void beanDefWithSeveralBeansInConfig() {
        String beanName1 = "bean1";
        String beanName2 = "bean2";

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName1, beanName2);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {
                return null;
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        List<String> beanDefinitions = simpleIoC.beanDefinitions();

        assertEquals(Arrays.asList(beanName1, beanName2), beanDefinitions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void beanNamesInConfigShouldBeUnique() {
        String beanName1 = "bean1";
        String beanName2 = "bean1";

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                if (beanName1.equals(beanName2)) {
                    throw new IllegalArgumentException();
                }

                return Arrays.asList(beanName1, beanName2);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {
                return null;
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        List<String> beanDefinitions = simpleIoC.beanDefinitions();
    }

    @Test
    public void getBeanWithOneBeanInConfig() throws Exception {

        String beanName = "beanName";
        Class<?> beanClass = TestBean.class;

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {
                return new BeanDefinition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return beanClass;
                    }
                };
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        TestBean testBean = (TestBean) simpleIoC.getBean(beanName);

        assertNotNull(testBean);
    }

    @Test
    public void beanShouldBeTheSame() throws Exception {

        String beanName = "beanName";
        Class<?> beanClass = TestBean.class;

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {
                return new BeanDefinition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return beanClass;
                    }
                };
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        TestBean testBean1 = (TestBean) simpleIoC.getBean(beanName);
        TestBean testBean2 = (TestBean) simpleIoC.getBean(beanName);

        assertSame(testBean1, testBean2);
    }

    @Test
    public void getBeanWithDependencies() throws Exception {

        String testBeanName = "testBean";
        Class<?> testBeanClass = TestBean.class;

        String dependentBeanName = "testBeanWithDependency";
        Class<?> dependentBeanClass = TestBeanWithDependency.class;

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(testBeanName, dependentBeanName);
            }

            @Override
            public BeanDefinition beanDefinition(String beanName) {

                Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
                beanDefinitions.put(testBeanName,
                        new BeanDefinition() {
                            @Override
                            public String getBeanName() {
                                return testBeanName;
                            }

                            @Override
                            public Class<?> getBeanClass() {
                                return testBeanClass;
                            }
                        }
                );

                beanDefinitions.put(dependentBeanName,
                        new BeanDefinition() {
                            @Override
                            public String getBeanName() {
                                return dependentBeanName;
                            }

                            @Override
                            public Class<?> getBeanClass() {
                                return dependentBeanClass;
                            }
                        }
                );

                return beanDefinitions.get(beanName);
            }
        };

        SimpleIoC simpleIoC = new SimpleIoC(config);
        TestBeanWithDependency testBeanWithDependency = (TestBeanWithDependency) simpleIoC.getBean(dependentBeanName);
        TestBean testBean = testBeanWithDependency.testBean;

        assertNotNull(testBean);
    }

    static class TestBean {
    }

    static class TestBeanWithDependency {
        public final TestBean testBean;

        public TestBeanWithDependency(TestBean testBean) {
            this.testBean = testBean;
        }
    }
}
