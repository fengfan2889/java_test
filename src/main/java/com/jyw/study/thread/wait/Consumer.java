package com.jyw.study.thread.wait;

/**
 * @author moongeek
 */
public class Consumer extends Thread{
    /**
     * 每次消费的产品数量
     */
    private int num;

    /**
     * 所在放置的仓库
     */
    private AbstractStorage abstractStorage1;

    /**
     * 构造函数，设置仓库
     * @param abstractStorage1 仓库
     */
    public Consumer(AbstractStorage abstractStorage1)
    {
        this.abstractStorage1 = abstractStorage1;
    }


    @Override
    public void run()
    {
        consume(num);
    }

    /**
     * 调用仓库Storage的消费函数
     * @param num 消费数量
     */
    public void consume(int num)
    {
        abstractStorage1.consume(num);
    }

    public void setNum(int num){
        this.num = num;
    }
}