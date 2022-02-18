package com.jyw.study.log;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;

/**
 * 日志打印类
 *
 * @author jyw
 */
public class LogJyw {

    public static void print(String logStr){
        System.out.println(DateUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN) + " : " + Thread.currentThread().getName() + " " + logStr);
    }
}
