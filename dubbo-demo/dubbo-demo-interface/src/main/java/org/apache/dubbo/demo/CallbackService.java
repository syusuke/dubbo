package org.apache.dubbo.demo;

/**
 * @author VcKerry on 2020/1/16
 */

public interface CallbackService {

    /**
     * add
     *
     * @param key
     * @param listener
     */
    void addListener(String key, CallbackListener listener);

}
