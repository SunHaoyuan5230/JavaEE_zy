package com.yuan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//多线程之间按顺序调用
public class ThreadOrderAccess {
    public static void main(String[] args) {

        ResourcesClass resourcesClass = new ResourcesClass();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resourcesClass.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resourcesClass.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resourcesClass.print15();
            }
        }, "C").start();
    }
}

class ResourcesClass {

    private Lock lock = new ReentrantLock();
    private Condition p1 = lock.newCondition();
    private Condition p2 = lock.newCondition();
    private Condition p3 = lock.newCondition();
    private Integer flag = 1;

    void print5() {
        lock.lock();
        try {
            while (flag != 1)
                p1.await();
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);

            }
            flag = 2;
            p2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void print10() {
        lock.lock();
        try {
            while (flag != 2)
                p2.await();

            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }

            flag = 3;
            p3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void print15() {
        lock.lock();
        try {
            while (flag != 3)
                p3.await();
            for (int i = 1; i <= 15; i++) {
                System.out.println(i);
            }

            flag = 1;
            p1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
