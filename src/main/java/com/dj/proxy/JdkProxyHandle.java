package com.dj.proxy;

import java.lang.reflect.Method;

public interface JdkProxyHandle {
    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable;
}
