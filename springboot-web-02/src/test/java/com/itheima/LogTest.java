package com.itheima;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LogTest {
    
    @Test
    public void testLog() {
        log.debug("Controller测试--debug");
        log.info("Controller测试--info");
        log.warn("Controller测试--warn");
        log.error("Controller测试--error");
    }
}
