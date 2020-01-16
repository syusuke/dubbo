# Dubbo-Spring XML

> 引导过程由Spring Namespace Handler 发起.

0. resources/META-INF/spring.handlers

   1. ```
      http\://dubbo.apache.org/schema/dubbo=org.apache.dubbo.config.spring.schema.DubboNamespaceHandler
      ```

1. org.apache.dubbo.config.spring.schema.DubboNamespaceHandler#init

2. org.apache.dubbo.config.spring.schema.DubboNamespaceHandler#parse
   1. 注册了`Spring`的`ApplicationListener`, 分别是
   2. `org.apache.dubbo.config.spring.context.DubboBootstrapApplicationListener`
   3. `org.apache.dubbo.config.spring.context.DubboLifecycleComponentApplicationListener`
3. org.apache.dubbo.config.spring.context.DubboLifecycleComponentApplicationListener#onApplicationContextEvent
4. org.apache.dubbo.config.spring.context.DubboLifecycleComponentApplicationListener#onContextRefreshedEvent
5. org.apache.dubbo.config.spring.context.DubboBootstrapApplicationListener#onApplicationContextEvent
6. org.apache.dubbo.config.spring.context.DubboBootstrapApplicationListener#onContextRefreshedEvent
   1. call start dubbo
7. org.apache.dubbo.config.bootstrap.DubboBootstrap#start
8. org.apache.dubbo.config.bootstrap.DubboBootstrap#initialize
   1. org.apache.dubbo.config.bootstrap.DubboBootstrap#startConfigCenter
   2. org.apache.dubbo.config.context.ConfigManager#refreshAll (写入配置信息到对象)
9. org.apache.dubbo.config.bootstrap.DubboBootstrap#exportServices

