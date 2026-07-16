package com.itheima;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootWeb02ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootWeb02ApplicationTests.class);

    @Test
    void contextLoads() {
        logger.debug("测试脚本--debug");
        logger.info("测试脚本--info");
    }

}
