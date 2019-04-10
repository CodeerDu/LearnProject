package com.dj.proxy;

import java.lang.reflect.Method;

public class JdkProxyTest implements JdkProxyHandle {
//    持有代理对象
    private JdkProxyObj obj;

    public Object getInstnce(JdkProxyObj target){
        this.obj = target;
        Class<?> clazz = obj.getClass();
        return JdkProxy.newProxyInstance(new JdkProxyClassLloader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(this.obj,args);
        after();
        return null;
    }

    private void before() {
        System.out.println("prepar");
    }

    private void after() {
        System.out.println("think");
    }

    public static void main(String[] args) {
        ExecInterface obj  = (ExecInterface) new JdkProxyTest().getInstnce(new JdkProxyObj());
        obj.doSomething();
    }
}
