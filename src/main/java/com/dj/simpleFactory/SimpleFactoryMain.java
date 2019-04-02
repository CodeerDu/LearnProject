package com.dj.simpleFactory;

public class SimpleFactoryMain {
    /**
     * 简单工厂模式（Simple Factory Pattern）是指由一个工厂对象决定创建出哪一种产品
     * 类的实例
     * @param args
     */
    public static  void main(String []args){
        ISoftFactory factory = new OASoft();
        factory.exec();
    }


}