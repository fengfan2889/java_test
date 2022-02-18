package com.jyw.study.pattern.strategy;

import com.jyw.study.pattern.strategy.impl.OperationAdd;
import com.jyw.study.pattern.strategy.impl.OperationMultiply;
import com.jyw.study.pattern.strategy.impl.OperationSubtract;

/**
 * @author jyw
 */
public class StrategyPatternDemo {

   public static void main(String[] args) {
      Context context = new Context(new OperationAdd());
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationSubtract());
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationMultiply());
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
   }
}
