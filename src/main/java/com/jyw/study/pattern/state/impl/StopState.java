package com.jyw.study.pattern.state.impl;

import com.jyw.study.pattern.state.Context;
import com.jyw.study.pattern.state.State;

/**
 * @author jyw
 */
public class StopState implements State {
 
   @Override
   public void doAction(Context context) {
      System.out.println("Player is in stop state");
      context.setState(this); 
   }
 
   @Override
   public String toString(){
      return "Stop State";
   }
}
