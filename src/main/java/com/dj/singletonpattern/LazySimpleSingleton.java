package com.dj.singletonpattern;

/**
 * 被外部类调用的时候内部类才会加载
 */
public class LazySimpleSingleton {
    private static  LazySimpleSingleton singleton = null;
    private  LazySimpleSingleton(){

    }

    public static  LazySimpleSingleton getInstance(){
        if(singleton == null){
            singleton = new LazySimpleSingleton();
        }
        return singleton;
    }

}
