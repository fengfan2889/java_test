package com.jyw.study.pattern.state;

import com.jyw.study.pattern.state.impl.StartState;
import com.jyw.study.pattern.state.impl.StopState;

/**
 * @author jyw
 */
public class StatePatternDemo {
   public static void main(String[] args) {
      Context context = new Context();
 
      StartState startState = new StartState();
      startState.doAction(context);
 
      System.out.println(context.getState().toString());
 
      StopState stopState = new StopState();
      stopState.doAction(context);
 
      System.out.println(context.getState().toString());
   }
}
