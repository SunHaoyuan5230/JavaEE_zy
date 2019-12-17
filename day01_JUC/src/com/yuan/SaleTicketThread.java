package com.yuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketThread {
    public static void main(String[] args) {
        Ticket1 ticket1 = new Ticket1();
        new Thread(() -> {
            for (int i = 1; i < 500; i++) ticket1.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 500; i++) ticket1.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 500; i++) ticket1.sale();
        }, "C").start();
    }
}

//资源类
class Ticket1 {
    private Lock lock = new ReentrantLock();
    private Integer tickets = 100;

    void sale() {
        lock.lock();
        try {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "售出第" + (101 - tickets) + "张车票,剩余" + --tickets + "张车票");
            }
        } finally {
            lock.unlock();
        }
    }
}
