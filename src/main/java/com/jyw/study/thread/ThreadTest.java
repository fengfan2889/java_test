package com.jyw.study.thread;

/**
 * @author jyw
 */
public class ThreadTest {



    public static void main(String[] args){
        threadRun(1);
    }


    /**
     * 执行一次线程，休息10秒
     */
    protected static void threadRun(int i) {
        new Thread(new Sleep10Thread(i)).start();
    }
}
