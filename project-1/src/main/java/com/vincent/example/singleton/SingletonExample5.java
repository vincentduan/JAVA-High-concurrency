package com.vincent.example.singleton;

import com.vincent.annotation.NotRecommend;
import com.vincent.annotation.NotThreadSafe;
import com.vincent.annotation.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例的实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingletonExample5 {
    //私有构造函数
    private SingletonExample5(){

    }
    //单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private static volatile SingletonExample5 instance = null;

    //静态工厂方法
    public static SingletonExample5 getInstance(){
        if(instance == null){ //双重检测机制
            synchronized (SingletonExample5.class) { //同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
