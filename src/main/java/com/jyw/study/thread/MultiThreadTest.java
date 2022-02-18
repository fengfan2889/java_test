package com.jyw.study.thread;

import java.util.stream.IntStream;

/**
 * @author jyw
 */
public class MultiThreadTest extends ThreadTest{

    public static void main(String[] args){
        IntStream.range(1,100).forEach(ThreadTest::threadRun);
    }




}
