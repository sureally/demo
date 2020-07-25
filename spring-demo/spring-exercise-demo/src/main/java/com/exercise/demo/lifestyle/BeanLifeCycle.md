---
layout: article
title: BeanLifeCycle
date: 2020/7/23 00:56
categories:
tags:
root-path: ../..
----------------


# Bean 生命周期

- 狭义的生命周期
Bean --> new 对象() --> 填充属性 --> Aware --> 初始化 --> AOP --> 单例池（Map<BeanName, 对象>）

AOP 后的对象是经过代理了的。

- 广义

Bean -->   beanDefinition    --> new 对象() --> 填充属性 --> Aware --> 初始化 --> AOP --> 单例池（Map<BeanName, 对象>）

           BeanDefinition
