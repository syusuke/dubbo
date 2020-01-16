package org.apache.dubbo.demo;

/**
 * @author VcKerry on 2020/1/16
 */

public interface CallbackListener {
    /**
     * callback listener
     *
     * @param msg
     */
    void changed(String msg);
}
