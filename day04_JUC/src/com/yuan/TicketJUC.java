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
        //三个售票员：一池固定3线程
        // ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //一个售票员:一池1线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //多个售票员:一池多线程(可自动扩容)
        ExecutorService threadPool = Executors.newCachedThreadPool();



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

    }
}

class TicketThr {
    private Lock lock = new ReentrantLock();
    private Integer tickets = 30;

    void tickSale() {
        lock.lock();
        try {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "\t售出第" + (31 - tickets) + "张车票,剩余" + --tickets + "张车票");
            }
        } finally {
            lock.unlock();
        }
    }
}
