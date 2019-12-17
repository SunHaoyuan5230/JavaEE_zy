package com.yuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * c. 完成一个卖票的程序，创建三个窗口同时卖票，三个窗口共享一个票资源！（选作）
 *	1. 解决线程不安全的问题       将访问敏感数据的代码上锁(在多线程的环境下，如果有线程进入到上锁的代码，它就有权利一直执行完毕)
 *		a. 同步锁的语法
 *			synchronized (锁对象) {需要上锁的代码}
 *				注意：锁对象可以是任意对象  多个线程必须在同一个锁对象下（ 字符串、this、this.getClass）
 *		b. public synchronized boolean test()  在方法上上锁
 *		c.
 *			//创建一个锁对象
 Lock lock=new ReentrantLock();
 //在需要上锁代码块的上方   锁住
 lock.lock();
 在代码块的下方  释放锁    无论如何锁都要释放    将释放锁的代码放在finally中
 lock.unlock();

 2. 死锁    要避免
 a. lock的释放锁没有执行
 b. synchronized导致死锁     锁与锁之间的相互等待
 */
public class Demo6 {

    public static void main(String[] args) {
		/*Thread61 t1=new Thread61();
		t1.start();

		Thread61 t2=new Thread61();
		t2.start();

		Thread61 t3=new Thread61();
		t3.start();*/

        Thread62 t=new Thread62();//count=10  只属于t对象

        Thread t1=new Thread(t);
        t1.start();

        Thread t2=new Thread(t);
        t2.start();

        Thread t3=new Thread(t);
        t3.start();

    }
}
class Thread61 extends Thread{
    private static int count=10;
    @Override
    public void run() {
        while(true){//卖票的循环   票卖没了循环就结束
            synchronized (this.getClass()) {//锁对象可以是任意对象  多个线程必须在同一个锁对象下 字符串
                if(count<=0){
                    System.out.println("已经卖没了");
                    break;
                }else{
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"买了一张票，剩余票数："+(--count));
                }
            }


        }
    }
}

class Thread62 implements Runnable{
    private int count=10;
    //创建一个锁对象
    Lock lock=new ReentrantLock();
    @Override
    public void run() {

        while(true){//卖票的循环   票卖没了循环就结束
			/*synchronized ("dfdg") {
				if(count<=0){
					System.out.println("已经卖没了");
					break;
				}else{
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"买了一张票，剩余票数："+(--count));
				}
			}*/
			/*if(test())
				break;*/

            //第三种上锁的方式   Lock
            lock.lock();//上锁的方法
            try {
                if(count<=0){
                    System.out.println("已经卖没了");
                    break;
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"买了一张票，剩余票数："+(--count));
                }
            } finally{
                lock.unlock();//释放锁       无论如何锁都要释放    将释放锁的代码放在finally中
            }

        }
    }

	/*public synchronized boolean test(){
		if(count<=0){
			System.out.println("已经卖没了");
			return true;
		}else{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"买了一张票，剩余票数："+(--count));
		}
		return false;
	}*/

}


