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
package com.alibaba.dubbo.registry;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * RegistryFactory. (SPI, Singleton, ThreadSafe)
 *
 * @see com.alibaba.dubbo.registry.support.AbstractRegistryFactory
 */
@SPI("dubbo")
public interface RegistryFactory {

    /**
     * Connect to the registry = 连接到注册中心
     * <p>
     * Connecting the registry needs to support the contract: <br>
     * 1. When the check=false is set, the connection is not checked, otherwise the exception is thrown when disconnection <br>
     * <p>设置Check=False,连接是不检查, 否则: Check=True,时连接不上会抛出异常
     * 2. Support username:password authority authentication on URL.<br>
     * <p>URL支持 username:password 认证
     * 3. Support the backup=10.20.153.10 candidate registry cluster address.<br>
     * <p> 支持备用地址注册集群中心
     * 4. Support file=registry.cache local disk file cache.<br>
     * <p>本地文件
     * 5. Support the timeout=1000 request timeout setting.<br>
     * <p>请求超时
     * 6. Support session=60000 session timeout or expiration settings.<br> Session超时
     *
     * @param url Registry address, is not allowed to be empty 注册中心地址，不允许为空 @NotNull
     * @return Registry reference, never return empty value 册中心引用，总不返回空 @NotNull
     */
    @Adaptive({"protocol"})
    Registry getRegistry(URL url);

}