package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.CallbackListener;
import org.apache.dubbo.demo.CallbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author VcKerry on 2020/1/16
 */

public class CallbackServiceImpl implements CallbackService {

    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();

    public CallbackServiceImpl() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000); // 定时触发变更通知
                    } catch (Throwable t) { // 防御容错
                        t.printStackTrace();
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }
    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        // 发送变更通知
        listener.changed(getChanged(key));
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
