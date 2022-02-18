package com.jyw.study.pattern.strategy.impl;

import com.jyw.study.pattern.strategy.Strategy;

/**
 * @author jyw
 */
public class OperationSubtract implements Strategy {

   @Override
   public int doOperation(int num1, int num2) {
      return num1 - num2;
   }
}
