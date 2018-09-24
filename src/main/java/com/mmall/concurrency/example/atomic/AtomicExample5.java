package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
    //AtomicIntegerFieldUpdater 更新某个类的指定字段，该字段必须用volatile修饰
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;//必须用 volatile 修饰

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            //log.info("update success 1, {}", example5.getCount());
            System.out.println("update success 1:"+example5.count);
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            //log.info("update success 2, {}", example5.getCount());
            System.out.println("update success 2:"+example5.count);
        } else {
            //log.info("update failed, {}", example5.getCount());
            System.out.println("update failed:"+example5.count);
        }
    }
}
