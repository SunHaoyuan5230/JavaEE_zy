package com.yuan;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExp {
    public static void main(String[] args) {

        Resources resources = new Resources();


        for (int i = 1; i <= 1000; i++) {
            final int tempI = i;
            new Thread(() -> {
                resources.write(tempI + "", tempI + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 1000; i++) {
            final int tempI = i;
            new Thread(() -> {
                resources.read(tempI + "");
            }, String.valueOf(i)).start();
        }
    }
}

class Resources {

    /**
     * 使用读写锁之后，写操作被锁，读写分离，且并发性好
     * 写入--唯一，读取--并发且高性能
     */
    private volatile Map<String, String> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    void read(String k) {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取开始：");
            System.out.println(map.get(k));
            System.out.println(Thread.currentThread().getName() + "\t读取结束！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    void write(String k, String v) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入开始：");
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "\t写入结束！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    /*高并发时开始混乱，并且并发性能下降

    void read(String k) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取开始：");
            System.out.println(map.get(k));
            System.out.println(Thread.currentThread().getName() + "\t读取结束！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void write(String k, String v) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入开始：");
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "\t写入结束！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    /*未加锁，读写混乱，bug遍地
    void read(String k) {
        System.out.println(Thread.currentThread().getName() + "\t读取开始：");
        System.out.println(map.get(k));
        System.out.println(Thread.currentThread().getName() + "\t读取结束！");
    }

    void write(String k, String v) {
        System.out.println(Thread.currentThread().getName() + "\t写入开始：");
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "\t写入结束！");
    }*/
}