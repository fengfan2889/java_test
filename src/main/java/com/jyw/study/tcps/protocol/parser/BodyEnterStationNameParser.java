package com.jyw.study.tcps.protocol.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 线路名称
 *
 * @author jyw
 */
class BodyEnterStationNameParser extends BodyParser{

    private static final int UP_FLAG = 0X00 ;
    private static final int DOWN_FLAG = 0XFF;

    @Override
    public void parser(String[] subBody) {

        int stationNameLength = subBody.length - 3  ;
        switch (Integer.parseInt(subBody[0],16)){
            case UP_FLAG:
                addLog("上下行标志：" + "上行");
                break;
            case DOWN_FLAG:
                addLog("上下行标志：" + "下行");
                break;
            default:
                addLog("上下行标志：" + "未知 " + subBody[0]);
                break;
        }
        int stationSerial = Integer.parseInt(subBody[1] + subBody[2], 16);
        addLog("站点序号：" + stationSerial + "," + subBody[1] + subBody[2]);
        String[] stationNameArray = Arrays.copyOfRange(subBody, 3 , 3+stationNameLength);
        String stationName = HexStringArrayToString(stationNameArray) ;
        addLog("站名（"+3 +"," + (3 +stationNameLength)+"):" + stationName + "," + Arrays.stream(stationNameArray).collect(Collectors.joining(" ")));

    }

}