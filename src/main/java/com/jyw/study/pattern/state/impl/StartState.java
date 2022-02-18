package com.jyw.study.pattern.state.impl;

import com.jyw.study.pattern.state.Context;
import com.jyw.study.pattern.state.State;

/**
 * @author jyw
 */
public class StartState implements State {
 
   @Override
   public void doAction(Context context) {
      System.out.println("Player is in start state");
      context.setState(this); 
   }
 
   @Override
   public String toString(){
      return "Start State";
   }
}
