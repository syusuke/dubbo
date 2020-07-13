package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author VcKerry on 2020/1/15
 */

public class GreetingServiceImpl implements GreetingService {

    private static Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

    @Override
    public String hello() {
        logger.info("hello. ");
        return " say " + LocalDateTime.now();
    }

    @Override
    public Object call() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public LocalDateTime getTime(String input) {
        return null;
    }

    @Override
    public LocalDateTime getTime(int input) {
        return null;
    }
}
