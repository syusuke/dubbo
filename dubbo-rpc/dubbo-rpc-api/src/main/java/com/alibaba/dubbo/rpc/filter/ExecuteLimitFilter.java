/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

import java.util.concurrent.Semaphore;

/**
 * ThreadLimitInvokerFilter
 */
@Activate(group = Constants.PROVIDER, value = Constants.EXECUTES_KEY)
public class ExecuteLimitFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        String methodName = invocation.getMethodName();
        Semaphore executesLimit = null;
        boolean acquireResult = false;
        // 获得服务提供者每服务每方法最大可并行执行请求数
        int max = url.getMethodParameter(methodName, Constants.EXECUTES_KEY, 0);
        if (max > 0) {
            // 获得 RpcStatus 对象，基于服务 URL + 方法维度
            RpcStatus count = RpcStatus.getStatus(url, invocation.getMethodName());
//            if (count.getActive() >= max) {
            /**
             * http://manzhizhen.iteye.com/blog/2386408
             * use semaphore for concurrency control (to limit thread number)
             */
            executesLimit = count.getSemaphore(max);
            if (executesLimit != null && !(acquireResult = executesLimit.tryAcquire())) {
                throw new RpcException("Failed to invoke method " + invocation.getMethodName() + " in provider " + url + ", cause: The service using threads greater than <dubbo:service executes=\"" + max + "\" /> limited.");
            }
        }
        long begin = System.currentTimeMillis();
        boolean isSuccess = true;
        RpcStatus.beginCount(url, methodName);
        try {
            return invoker.invoke(invocation);
        } catch (Throwable t) {
            isSuccess = false;
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RpcException("unexpected exception when ExecuteLimitFilter", t);
            }
        } finally {
            RpcStatus.endCount(url, methodName, System.currentTimeMillis() - begin, isSuccess);
            // 释放信号量
            if (acquireResult) {
                executesLimit.release();
            }
        }
    }

}
