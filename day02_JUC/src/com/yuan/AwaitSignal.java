package com.yuan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignal {
    public static void main(String[] args) {
        JiaoTiLock jt = new JiaoTiLock();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                jt.add();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                jt.dec();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                jt.add();
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                jt.dec();
            }
        },"D").start();

    }
}


class JiaoTiLock {
    private Integer num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void add() {
        lock.lock();
        try {
            while (num != 0)
                condition.await();
            num++;
            System.out.println(Thread.currentThread().getName() + "\t "+num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void dec() {
        lock.lock();
        try {
            while (num == 0)
                condition.await();
            num--;
            System.out.println(Thread.currentThread().getName()+ " \t "+num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}