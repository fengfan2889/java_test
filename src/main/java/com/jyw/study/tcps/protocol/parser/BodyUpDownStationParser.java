package com.jyw.study.tcps.protocol.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 0x21 0x22 上行以及下行站点设置
 *
 * @author jyw
 */
class BodyUpDownStationParser extends BodyParser{

    @Override
    public void parser(String[] subBody) {
        if (subBody.length ==0){
            addLog("包体为空");
            return ;
        }
        int i = 0 ;
        int bodySerial = 1 ;
        int languageCount = Integer.parseInt(subBody[i],16);
        addLog(bodySerial, "语种数量(1,1):" + subBody[i]) ;
        i = i + 1 ;
        while (i < subBody.length){
            int stationSerial = Integer.parseInt(subBody[i ] + subBody[i+1],16);
            addLog(bodySerial, "站点序号（2,3):" + subBody[i ] + subBody[i+1] + "," + stationSerial) ;
            i = i + 2 ;
            for (int j = 0; j < languageCount; j++) {
                int stationNameLength = Integer.parseInt(subBody[i],16);
                addLog(bodySerial,  "第" + j + "语种站名长度（1,4):" + subBody[i] + "," + stationNameLength);
                String[] stationNameArray = Arrays.copyOfRange(subBody, i+1 , i+1+stationNameLength);
                String stationName = HexStringArrayToString(stationNameArray) ;
                addLog(bodySerial, "第" + j + "语种站名（"+stationNameLength +"," + (i + 1 +stationNameLength)+"):" + stationName + "," + Arrays.stream(stationNameArray).collect(Collectors.joining(" ")));
                i = i + 1 + stationNameLength ;
            }
        }
    }

    private void addLog(int bodySerial,String logStr){
        addLog(String.format("%02d,%s",bodySerial,logStr));
    }
}