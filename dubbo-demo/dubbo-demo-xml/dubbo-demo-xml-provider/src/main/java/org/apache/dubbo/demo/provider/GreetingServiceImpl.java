package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author VcKerry on 2020/1/16
 */

public class GreetingServiceImpl implements GreetingService {
    private static Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

    @Override
    public String hello() {

        logger.warn("call hello() method ");

        return "Call hello() method resposne....";

    }
}
