package com.dj.fatoryMethod;

/**
 * 工厂方法模式（Fatory Method Pattern）是指定义一个创建对象的接口，但让实现这
 * 个接口的类来决定实例化哪个类，工厂方法让类的实例化推迟到子类中进行
 */
public class FactoryMethodMain {
    public static  void main(String []args){
        ISoft soft = new MobileSoftFactory().getInstance();
        soft.ecec();
    }
}
