package com.jyw.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author jyw
 */
public class SingleThreadPoolThreadTest extends ThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(1,19).forEach(i->executorService.execute(new Sleep10Thread()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(1,19).forEach(i->executorService.execute(new Sleep10Thread()));
        //executorService.shutdownNow();
    }


}
