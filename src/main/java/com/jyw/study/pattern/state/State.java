package com.jyw.study.pattern.state;

/**
 * @author jyw
 */
public interface State {

   /**
    * 行为
    * @param context 上下文
    */
   void doAction(Context context);
}
