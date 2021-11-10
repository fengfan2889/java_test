package com.jyw.study.tcps.protocol.parser;

import cn.hutool.core.util.StrUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author jyw
 */
public abstract class BodyParser {

    public static final String PROTOCOL_HEADER = "BB" ;
    public static final String PROTOCOL_FOOTER = "55" ;

    public static final int PROTOCOL_HEADER_LENGTH = 10 ;

    private List<String> sb ;
    private String prefix ;

    /**
     * 添加前缀后输入到日志列表中
     *
     * @param log
     */
    protected void addLog(String log){
        sb.add(prefix+ log);
    }

    /**
     * 将16进制字符串转换为字符串输出
     *
     * @param bodys
     * @return
     */
    protected String HexStringArrayToString(String[] bodys){
        Byte[] srcByteArray = Arrays.stream(bodys).map(s -> Integer.valueOf(s, 16).byteValue()).toArray(Byte[]::new);
        byte[] ss = new byte[srcByteArray.length];
        IntStream.range(0, srcByteArray.length).forEach(j -> ss[j] = srcByteArray[j]);
        return new String(ss, StandardCharsets.UTF_8);
    }

    /**
     * 将一个body解析到list中
     *
     * @param subBody 包体
     */
    abstract public void parser(String[] subBody);

    private static final Map<String,String> BODY_TYPE_MAP = new HashMap<String, String>(){
        {
            put("00","设备自检 0 字节，无数据");
            put("01","设置时间 ");
            put("02","上行、下行标志 1 字节 上行：0x01 下行：0x02");
            put("03","站点类型1 字节上行首站： 0x01 上行末站： 0x02下行首站： 0x03 下行末站： 0x04 中间站： 0x05");
            put("04","到离站标识 1 字节 到站：0x01 离站： 0x02 ");
            put("05","站序号 2 字节 与设置相对应");
            put("06","宣传语编号1 字节与宣传用语设置中的编号一致;目前暂设为 0x01~0x08");
            put("07","单次宣传用语 ");
            put("08","设置站名称 ");
            put("0A","设置站节信息 ");
            put("10","硬件版本号 ");
            put("11","软件版本号 ");
            put("12","设备故障 ");
            put("21","上行站设置");
            put("22","下行站设置 ");
            put("23","首末站用语设置 ");
            put("24","宣传用语设置 ");
            put("25","宣传用语删除 ");
            put("26","增加单条宣传用语 ");
            put("30","线路特征词 ");
            put("31","线路名称 ");
            put("32","设置始发站信息 ");
            put("33","设置终点站信息 ");
            put("40","数据传输中止命令长度:0x00当批量传输数据时，如果主机发现传输不可靠或有紧急任务时，在收到数据包后，会发送中止命令的子包。");
            put("41","重发命令包 1 字节，需要重发包的流水号。");
            put("99","车辆状态信息 ");
            put("A0","数据透传指令 ");
            put("FF","回复包表示该流水号通讯包已接受并处理共 2 字节，包含以下部分子包类型：1 字节。处理结果：1 字节。0x00 成功 0x01~0xff");
        }

    };

    public static String queryBodyTypeDescription(String bodyType){
        String result = BODY_TYPE_MAP.get(bodyType) ;
        if (StrUtil.isEmpty(result)){
            result = "Unknown" ;
        }
        return  result;
    }


    public void setSb(List<String> sb) {
        this.sb = sb;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}