package com.dj.singletonpattern;

public class ExectorThread implements  Runnable{
    public void run() {
//        System.out.println(Thread.currentThread()+"   "+LazySimpleSingleton.getInstance());
        System.out.println(Thread.currentThread().getId()+"  "+EnumSingleton.getSingleton().toString());
    }
}
