package com.jyw.study.tcps.protocol.parser;

/**
 * 到离站标志解析
 *
 * @author jyw
 */
public class BodyStationEnterExitFlagParser extends BodyParser {

    private static final int ENTER_FLAG = 1 ;
    private static final int EXIT_FLAG = 2 ;

    @Override
    public void parser(String[] subBody) {
        String upDownFlagCaption ;

        switch (Integer.parseInt(subBody[0], 16)){
            case ENTER_FLAG:
                upDownFlagCaption = "到站";
                break;
            case EXIT_FLAG:
                upDownFlagCaption = "离站";
                break;
            default:
                upDownFlagCaption = "未知" + subBody[0] ;
                break;
        }
        addLog("到离站标志：" + upDownFlagCaption) ;
    }
}
