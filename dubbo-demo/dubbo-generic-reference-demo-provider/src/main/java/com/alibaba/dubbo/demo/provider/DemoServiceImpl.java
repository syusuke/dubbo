package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.ParamCallback;

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return getClass().getName() + ":" + name;
    }

    @Override
    public void bye(Object o) {
    }

    @Override
    public void callbackParam(String msg, ParamCallback callback) {

    }

    @Override
    public String say01(String msg) {
        return "tst ... :" + msg;
    }

    @Override
    public String[] say02() {
        return new String[0];
    }

    @Override
    public void say03() {

    }

    @Override
    public Void say04() {
        return null;
    }

}