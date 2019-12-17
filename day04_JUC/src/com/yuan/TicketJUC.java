package com.yuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池做买票
 */
public class TicketJUC {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //三个售票员
        TicketThr ticketThr = new TicketThr();

        try {
            for (int i = 1; i <= 50; i++) {
                threadPool.execute(() -> {
                    ticketThr.tickSale();
                });
            }
        } finally {
            threadPool.shutdown();
        }

        /*new Thread(() -> {
            for (int i = 1; i < 500; i++) ticketThr.tickSale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 500; i++) ticketThr.tickSale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 500; i++) ticketThr.tickSale();
        }, "C").start();*/
    }
}

class TicketThr {
    private Lock lock = new ReentrantLock();
    private Integer tickets = 30;

    void tickSale() {
        lock.lock();
        try {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "售出第" + (31 - tickets) + "张车票,剩余" + --tickets + "张车票");
            }
        } finally {
            lock.unlock();
        }
    }
}
