package com.jyw.study.thread;

import com.jyw.study.log.LogJyw;

import java.util.Random;

/**
 * @author jyw
 */
public class Sleep10Thread implements Runnable{

    private int threadNumber;

    public Sleep10Thread() {

    }

    public Sleep10Thread(int i) {
        this.threadNumber = i;
    }

    @Override
    public void run() {
        if (threadNumber != 0) {
            Thread.currentThread().setName("Thread-" + String.format("%04d", threadNumber));
        }
        int sleepNumber = new Random().nextInt(15000) ;
        LogJyw.print(" run begin ----" + sleepNumber);
        try {
            Thread.sleep(sleepNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogJyw.print(" run end ----");
    }
}
