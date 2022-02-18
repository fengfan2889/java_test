package com.jyw.study.thread.wait;

/**
 * @author moongeek
 */
public interface AbstractStorage {
    /**
     * 消费一个产品
     * @param num 消费数量
     */
    void consume(int num);

    /**
     * 生产一个产品
     * @param num  生产数量
     */
    void produce(int num);
}