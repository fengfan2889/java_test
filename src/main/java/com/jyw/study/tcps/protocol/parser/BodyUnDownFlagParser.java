package com.jyw.study.tcps.protocol.parser;

/**
 * 上行下行标志解析
 *
 * @author jyw
 */
public class BodyUnDownFlagParser extends BodyParser {

    public static final int UP_FLAG = 1 ;
    public static final int DOWN_FLAG = 2 ;

    public static String queryUnDownFlagDescription(int upDownFlag){
        String upDownFlagCaption ;

        switch (upDownFlag){
            case UP_FLAG:
                upDownFlagCaption = "上行";
                break;
            case DOWN_FLAG:
                upDownFlagCaption = "下行";
                break;
            default:
                upDownFlagCaption = "未知" + upDownFlag ;
                break;
        }
        return upDownFlagCaption ;
    }

    @Override
    public void parser(String[] subBody) {
        String upDownFlagCaption = queryUnDownFlagDescription(Integer.parseInt(subBody[0],16));
        addLog("上下行标志：" + upDownFlagCaption) ;
    }
}
