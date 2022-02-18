package com.jyw.study.pattern.strategy;

/**
 * @author jyw
 */
public interface Strategy {

   /**
    * 策略模式测试，策略算法实现
    *
    * @param num1 操作数1
    * @param num2 操作数2 
    * @return 操作结果
    */
   int doOperation(int num1, int num2);
}
