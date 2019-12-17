package com.yuan;

public class WaitNotify {
    public static void main(String[] args) {

        JiaoTi jt = new JiaoTi();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    jt.Add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    jt.Dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    jt.Add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    jt.Dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//资源类
class JiaoTi {
    private Integer num = 0;

    public synchronized void Add() throws InterruptedException {
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }

    public synchronized void Dec() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();

    }
}
