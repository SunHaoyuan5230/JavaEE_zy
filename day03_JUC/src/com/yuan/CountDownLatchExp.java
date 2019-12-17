package com.yuan;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时阻塞线程
 */
public class CountDownLatchExp {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 参数10，是倒计时多少
         */
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+tempI);
                //倒计时位置
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //阻塞main进程
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t最后执行的进程！");

        /*未使用倒计时阻塞CountDownLatch时，最后执行的进程不一定会最后执行
        for (int i = 1; i <= 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+tempI);
            },String.valueOf(i)).start();
        }
        System.out.println("最后执行的进程！");*/
    }
}
