package com.yuan;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 多线程抢多资源
 * 线程多于资源
 */
public class SemaphoreExp {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                Boolean flag = false;
                try {
                    //占用/获取
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (flag) {
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
