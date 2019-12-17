package com.yuan;

import java.util.concurrent.TimeUnit;

public class Lock8 {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() -> {
            // phone1.sendMsg();
            phone2.sendMsg();
            // phone1.hello();
            },"B").start();
    }
}

/**
 *  1 标准访问，先打印短信还是邮件
 *      //先打印Email
 *  2 停3秒在短信方法内，先打印短信还是邮件
 *      //先打印Email
 *      一个类中有多个synchronized方法，多线程使用同一对象调用时，
 *      只能调用其中一个，另一个线程需要等待
 *      这时锁的是this对象，被锁后，其他线程不能进入当前对象的其他synchronized方法
 *
 *  3 普通的hello方法，是先打邮件还是hello
 *      //先打印hello
 *      普通方法与同步锁无关，所以可以调用
 *
 *  4 现在有两部手机，先打印短信还是邮件
 *      //先打印短信
 *      使用不同对象，锁的对象不同，所以可以调用
 *
 *  5 两个静态同步方法，1部手机，先打印短信还是邮件
 *      //先打印邮件
 *  6 两个静态同步方法，2部手机，先打印短信还是邮件
 *      //先打印邮件
 *      静态同步方法时，锁的是整个静态资源类
 *
 *  7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *      //先打印短信
 *  8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 *      //先打印短信
 *      静态同步方法用的锁是Class类模板，普通同步方法锁的是this对象本身，
 *      锁不同，所以可以调用。
*/

class Phone{
/*
    synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("****sendEmail");
    }

    synchronized void sendMsg(){
        System.out.println("****sendMsg");
    }*/
    static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("****sendEmail");
    }

    synchronized void sendMsg(){
        System.out.println("****sendMsg");
    }

    void hello(){
        System.out.println("****hello");
    }
}