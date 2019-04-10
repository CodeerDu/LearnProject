package com.dj.proxy;

import java.lang.reflect.Method;

/**
 * 代理类实现接口
 */
public interface JdkProxyHandle {
    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable;
}
