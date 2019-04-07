package com.dj.singletonpattern;

/**
 * 饿汉式单例是在类加载的时候就立即初始化，并且创建单例对象。绝对线程安全，在线
 * 程还没出现以前就是实例化了，不可能存在访问安全问题。
 */
public class HungrySingleton {
    private static  HungrySingleton hungrySingleton = new HungrySingleton();
    private static HungrySingleton hungrySingleton1;
    static {
        hungrySingleton1 = new HungrySingleton();
    }
    private  HungrySingleton(){};
    public static  HungrySingleton getInstance(){
            return hungrySingleton;
    }
}
