package com.yuan;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Auther: SunHaoyuan
 * @Date: 2019/12/17 19:52
 * @Description:
 */
public class CallableExp {
    public static void main(String[] args) throws Exception {


        FutureTask futureTask = new FutureTask(new Mythread());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}

class Mythread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("****Callable");
        return 1234;
    }
}