package com.jyw.study.tcps.protocol.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 线路名称
 *
 * @author jyw
 */
class BodyLineNameParser extends BodyParser{

    @Override
    public void parser(String[] subBody) {
        int i = 0 ;
        int languageCount = Integer.parseInt(subBody[i],16);
        addLog("语种数量(1,1):" + subBody[i]) ;
        i = i + 1 ;
        while (i < subBody.length){
            for (int j = 0; j < languageCount; j++) {
                int stationNameLength = Integer.parseInt(subBody[i],16);
                addLog(  "第" + j + "语种线路内容长度（1,2):" + subBody[i] + "," + stationNameLength);
                String[] lineNameArray = Arrays.copyOfRange(subBody, i+1 , i+1+stationNameLength);
                String stationName = HexStringArrayToString(lineNameArray) ;
                addLog("第" + j + "线路名称（"+stationNameLength +"," + (i + 1 +stationNameLength)+"):" + stationName + "," + Arrays.stream(lineNameArray).collect(Collectors.joining(" ")));
                i = i + 1 + stationNameLength ;
            }
        }
    }

}