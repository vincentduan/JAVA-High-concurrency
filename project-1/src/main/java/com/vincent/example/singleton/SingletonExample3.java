package com.vincent.example.singleton;

import com.vincent.annotation.NotRecommend;
import com.vincent.annotation.NotThreadSafe;
import com.vincent.annotation.ThreadSafe;

/**
 * 懒汉模式
 * 单例的实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance = null;
    //静态工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
