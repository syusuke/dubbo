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
package com.alibaba.dubbo.remoting.zookeeper;

import com.alibaba.dubbo.common.URL;

import java.util.List;

public interface ZookeeperClient {

    /**
     * 创建结点
     *
     * @param path
     * @param ephemeral 是否是临时结点
     */
    void create(String path, boolean ephemeral);

    /**
     * 删除结点
     *
     * @param path
     */
    void delete(String path);

    /**
     * ChildListener
     *
     * @param path 节点路径
     * @return
     */
    List<String> getChildren(String path);

    /**
     * 添加 ChildListener
     *
     * @param path
     * @param listener
     * @return
     */
    List<String> addChildListener(String path, ChildListener listener);

    /**
     * 移除ChildListener
     *
     * @param path
     * @param listener
     */
    void removeChildListener(String path, ChildListener listener);

    /**
     * 添加 StateListener
     *
     * @param listener
     */
    void addStateListener(StateListener listener);

    void removeStateListener(StateListener listener);

    boolean isConnected();

    void close();

    /**
     * 获得注册中心 URL
     *
     * @return
     */
    URL getUrl();

}
