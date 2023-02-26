package com.mxy.module.rxjava;


import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscriber;

@Slf4j
public class RxJavaDemo {

    public static void main(String[] args) {
        // 1. 创建被观察者
        Observable<String> observable = Observable.create(subscriber -> {
            subscriber.onNext("Hello world1.");
            subscriber.onNext("Hello world2.");
            subscriber.onNext("Hello world3.");
            //throw new RuntimeException(""); 会触发onError事件
            subscriber.onCompleted();
        });

        // 2. 创建观察者
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                log.info("onCompleted..." + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                log.info("onError..." + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                log.info("onNext: {},{}", s, Thread.currentThread().getName());
            }
        };
        // 3. 订阅事件
        observable.subscribe(subscriber);
    }


}
