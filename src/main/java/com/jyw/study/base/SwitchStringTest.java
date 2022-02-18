package com.jyw.study.base;

import com.jyw.study.log.LogJyw;

/**
 * @author jyw
 */
public class SwitchStringTest {

    public static void main(String[] args){
        // 】当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null 判断。
        String switchString = null;
        switch (switchString){
            case "null":
                LogJyw.print("null");
                break;
            case "a":
                LogJyw.print("a");
                break;
            case "b":
                LogJyw.print("b");
                break;

            default:
                LogJyw.print("default");
        }
    }
}
