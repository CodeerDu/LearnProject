package com.dj.singletonpattern;

/**
 * ThreadLocal 不能保证其
 * 创建的对象是全局唯一，但是能保证在单个线程中是唯一的，天生的线程安全
 */
public class ThreadLocalSingleton {
    private static ThreadLocal<ThreadLocalSingleton> threadLocalSingletonThreadLocal =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };
    private ThreadLocalSingleton(){};

    public static  ThreadLocalSingleton getInstance(){
        return threadLocalSingletonThreadLocal.get();
    }
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println(ThreadLocalSingleton.getInstance());
        }
        System.out.println("-----------------");
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(ThreadLocalSingleton.getInstance());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(ThreadLocalSingleton.getInstance());
            }
        });

        thread1.start();
        thread2.start();
    }
}
