package com.jyw.study.tcps.protocol.parser;


/**
 * @author jyw
 */
public class BodyStationSerialParser extends BodyParser{

    private static final int BODY_LENGTH_STATION_SERIAL = 2 ;
    @Override
    public void parser(String[] subBody) {
        if (subBody.length != BODY_LENGTH_STATION_SERIAL){
            addLog("包体长度错误，协议指定的长度 ： " + BODY_LENGTH_STATION_SERIAL + ",包体长度：" + subBody.length);
        }
        int stationSerial = Integer.parseInt(subBody[0] + subBody[1], 16);
        addLog("站点序号：" + stationSerial + "," + subBody[0] + subBody[1]);

    }
}
