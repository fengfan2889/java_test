package com.jyw.study.tcps.protocol.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点类型解析
 * @author jyw
 */
public class BodyStationTypeParser extends BodyParser{
    private static final int STATION_TYPE_UP_FIRST = 1 ;
    private static final int STATION_TYPE_UP_END = 2 ;
    private static final int STATION_TYPE_DOWN_FIRST = 3 ;
    private static final int STATION_TYPE_DOWN_END = 4 ;
    private static final int STATION_TYPE_MIDDLE = 5 ;

    private static Map<Integer,String> stationTypeDescMap = new HashMap<Integer,String>(){
        {
            put(STATION_TYPE_UP_FIRST,"上行首站");
            put(STATION_TYPE_UP_END,"上行末站");
            put(STATION_TYPE_DOWN_FIRST,"下行首站");
            put(STATION_TYPE_DOWN_END,"下行末站");
            put(STATION_TYPE_MIDDLE,"中间站");
        }
    };

    @Override
    public void parser(String[] subBody) {
        String result = stationTypeDescMap.get(Integer.parseInt(subBody[0],16));
        if(result == null){
            result = "未知 ：" + subBody[0] ;
        }
        addLog("站点类型：" + result);
    }
}
