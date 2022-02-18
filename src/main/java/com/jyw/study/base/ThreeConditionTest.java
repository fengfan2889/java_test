package com.jyw.study.base;

import com.jyw.study.log.LogJyw;

/**
 * @author jyw
 */
public class ThreeConditionTest {

    public static void main(String[] args) {
        // 三目运算符 condition? 表达式 1 : 表达式 2 中，高度注意表达式 1 和 2 在类型对齐时，可能抛出因自动拆箱导致的 NPE 异常。
        // 说明：以下两种场景会触发类型对齐的拆箱操作：
        // 1） 表达式 1 或表达式 2 的值只要有一个是原始类型。
        // 2） 表达式 1 或表达式 2 的值的类型不一致，会强制拆箱升级成表示范围更大的那个类型。
        Integer one = 1;
        Integer two = 2;
        Integer three = null;
        boolean flag = false;
        extracted(one, two, three, flag);
        System.out.println(Integer.MAX_VALUE * Integer.MAX_VALUE);
    }

    private static void extracted(Integer one, Integer two, Integer three, boolean flag) {
        // one * two 是int类型
        Integer result = flag ? one * two : three;
        LogJyw.print(String.valueOf(result));
    }
}
