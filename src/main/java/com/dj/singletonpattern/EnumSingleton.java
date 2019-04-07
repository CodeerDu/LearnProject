package com.dj.singletonpattern;

/**
 * 注册式单例又称为登记式单例，就是将每一个实例都登记到某一个地方，使用唯一的标
 * 识获取实例。注册式单例有两种写法：一种为容器缓存，一种为枚举登记。
 */
public enum  EnumSingleton {
    SINGLETON;
    private int attr1;

    public int getAttr1() {
        return attr1;
    }

    public void setAttr1(int attr1) {
        this.attr1 = attr1;
    }

    public static  EnumSingleton getSingleton(){
        return SINGLETON;
    }
}
