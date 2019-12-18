package com.yuan;

import java.util.concurrent.*;

/**
 * @Auther: SunHaoyuan
 * @Date: 2019/12/18 18:58
 * @Description:
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        try {
            for (int i = 1; i <= 8; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"办理第"+finalI+"号业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}

