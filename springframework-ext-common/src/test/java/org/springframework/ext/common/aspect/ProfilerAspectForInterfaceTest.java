package org.springframework.ext.common.aspect;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertThat;

/**
 * @author ouliyuan
 * @date 2017-12-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProfilerAspectForInterfaceTest.SpringConfig.class)
public class ProfilerAspectForInterfaceTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private ProfilerAspectInterface profilerAspectInterface;

    @Test
    public void callInterface() throws Exception {
        String actual = profilerAspectInterface.callMethod(888888L, "spring");

        assertThat(actual, CoreMatchers.is("888888spring"));
    }

    public interface ProfilerAspectInterface {
        String callMethod(Long id, String key);
    }

    public static class ProfilerAspectTarget implements ProfilerAspectInterface {
        @Profiler
        public String callMethod(Long id, String key) {
            return id + key;
        }
    }

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan
    public static class SpringConfig {
        @Bean
        public ProfilerAspect profilerAspect() {
            return new ProfilerAspect();
        }

        @Bean
        public ProfilerAspectInterface profilerAspectInterface() {
            return new ProfilerAspectTarget();
        }
    }
}
