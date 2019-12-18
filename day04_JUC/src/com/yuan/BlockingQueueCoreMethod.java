package com.yuan;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: SunHaoyuan
 * @Date: 2019/12/17 23:50
 * @Description:
 */
public class BlockingQueueCoreMethod {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(3);

        /**
         * 抛异常方法
         * 超过队列大小会报异常
         */

        /*插入

        System.out.println(bq.add("a"));
        System.out.println(bq.add("b"));
        System.out.println(bq.add("c"));
        // System.out.println(bq.add("d"));
        */

        /*移除
        System.out.println(bq.remove());
        System.out.println(bq.remove());
        System.out.println(bq.remove());
        System.out.println(bq.remove());
        */

        /*检查
        System.out.println(bq.element());
        */

        /**
         * 特殊值
         * 不会报错
         */

        /*插入
        * 超出队列大小会显示false
        System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));
        // System.out.println(bq.offer("d"));
        */

        /*移除
        * 超出队列大小会显示null
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        */

        /*检查
        System.out.println(bq.peek());
         */

        /**
         * 阻塞
         * 超出队列大小会阻塞进程
         */
        /*插入
        bq.put("a");
        bq.put("b");
        bq.put("c");
        System.out.println("***插入1");
        // bq.put("d");
        // System.out.println("***插入2");
        */

        /*移除
        bq.take();
        bq.take();
        bq.take();
        System.out.println("****移除1");
        bq.take();
        System.out.println("****移除2");
        */

        /**
         * 超时
         * 超时不再等待
         */
        /*插入*/
        System.out.println(bq.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(bq.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(bq.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println("****插入1");
        System.out.println(bq.offer("d", 2L, TimeUnit.SECONDS));
        System.out.println("****插入2");

        /*移除*/
        System.out.println(bq.poll(2L, TimeUnit.SECONDS));
        System.out.println(bq.poll(2L, TimeUnit.SECONDS));
        System.out.println(bq.poll(2L, TimeUnit.SECONDS));
        System.out.println("****删除1");
        System.out.println(bq.poll(2L, TimeUnit.SECONDS));
        System.out.println("****删除2");
    }
}