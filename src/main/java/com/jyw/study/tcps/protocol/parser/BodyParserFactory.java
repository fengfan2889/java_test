package com.jyw.study.tcps.protocol.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jyw
 */
public class BodyParserFactory {

    private static BodyParserFactory instance ;
    public static BodyParserFactory getInstance(){
        if (instance == null){
            instance = new BodyParserFactory();
        }
        return instance ;
    }

    public BodyParser getBodyParser(String bodyType){
            BodyParser result = BODY_PARSER_MAP.get(bodyType);
            if (result == null){
                result = new BodyUnknownParser();
            }
            return result ;
    }

        private static final Map<String,BodyParser> BODY_PARSER_MAP = new HashMap<String, BodyParser>(10){
            {
                put("02",new BodyUnDownFlagParser());
                put("03",new BodyStationTypeParser());
                put("04",new BodyStationEnterExitFlagParser());
                put("05",new BodyStationSerialParser());
                put("08",new BodyEnterStationNameParser());
                put("0A",new BodyConfigStationParser());
                put("21",new BodyUpDownStationParser());
                put("22",new BodyUpDownStationParser());
                put("31",new BodyLineNameParser());
                put("32",new BodyStationNameListParser());
                put("33",new BodyStationNameListParser());
            }
        };
}
