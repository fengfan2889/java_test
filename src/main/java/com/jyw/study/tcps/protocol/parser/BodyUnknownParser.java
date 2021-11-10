package com.jyw.study.tcps.protocol.parser;

/**
 * 未知解决直接尝试解析为字符串
 *
 * @author jyw
 */
class BodyUnknownParser extends BodyParser{

    @Override
    public void parser(String[] subBody) {
        addLog("解析内容：" + HexStringArrayToString(subBody));
    }
}