package com.jyw.study.tcps.protocol.parser;


/**
 * @author jyw
 */
public class BodyConfigStationParser extends BodyParser{

    private static final int BODY_LENGTH_CONFIG_STATION =4 ;
    @Override
    public void parser(String[] subBody) {
        if (subBody.length != BODY_LENGTH_CONFIG_STATION){
            addLog("包体长度错误，协议指定的长度 ： " + BODY_LENGTH_CONFIG_STATION + ",包体长度：" + subBody.length);
        }
        int upStationCount = Integer.parseInt(subBody[0] + subBody[1], 16);
        addLog("上行站点数量：" + upStationCount + "," + subBody[0] + subBody[1]);
        int downStationCount = Integer.parseInt(subBody[2] + subBody[3], 16);
        addLog("下行站点数量：" + downStationCount + "," + subBody[2] + subBody[3]);
    }
}
