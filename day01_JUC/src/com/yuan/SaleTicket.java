package com.yuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args) {

        //资源类
        Ticket ticket = new Ticket();
        //创建线程
        Thread t1 = new Thread(ticket, "A");
        t1.start();
        Thread t2 = new Thread(ticket, "B");
        t2.start();
        Thread t3 = new Thread(ticket, "C");
        t3.start();
        Thread t4 = new Thread(ticket, "D");
        t4.start();
    }
}

class Ticket implements Runnable {
    private Integer tickets = 1000;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (tickets > 0) {
            lock.lock();
            try {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "\t卖出第" + (1001 - tickets) + "张," + "剩余" + --tickets + "张");
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
