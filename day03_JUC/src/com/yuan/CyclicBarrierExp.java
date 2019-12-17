package com.yuan;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环屏障
 * 集齐七颗龙珠才能召唤神龙
 * 人到齐了才能开始开会
 */
public class CyclicBarrierExp {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠，成功召唤神龙！");
        });

        for (int i = 1; i <= 7; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t第" + tempI + "颗龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        /*CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println(Thread.currentThread().getName()+"最后执行的进程！");
        });
        for (int i = 1; i <= 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() +  "\t" + tempI );
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }*/




    }
}
