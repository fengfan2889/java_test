package com.jyw.study.tcps.protocol;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.jyw.study.tcps.protocol.parser.BodyParser;
import com.jyw.study.tcps.protocol.parser.BodyParserFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 一个led的解析
 * @author jyw
 */
public class TcpsProtocolLedSimple {

    public static void main(String[] args) {
        System.out.println("请输入文件名：");
        String fileName = Console.input();
        if (!FileUtil.exist(fileName)){
            System.out.println(fileName + "不存在 ");
            return ;
        }
        List<String> bodys =FileUtil.readLines(fileName,StandardCharsets.UTF_8);
        if (bodys == null){
            System.out.println(fileName + "文件为空或读取失败！");
            return ;
        }

        bodys.forEach(TcpsProtocolLedSimple::accept);

    }

    private static Set<String> containsSet = new HashSet<>();

    private static void accept(String item) {
        if(StrUtil.isEmpty(item)){
            return ;
        }
        String[] originArray = item.replaceAll(" {2}", "").split(" ");
        if (originArray.length == 0){
            return ;
        }
        if(containsSet.contains(item)){
            return ;
        }
        containsSet.add(item);
        if (!BodyParser.PROTOCOL_HEADER.equals(originArray[0]) || !BodyParser.PROTOCOL_FOOTER.equals(originArray[originArray.length - 1])) {
            System.out.println("包头包尾错误，离开");
            return ;
        }
        Boolean isVerify = checkVerify(originArray);
        if (!isVerify){
            System.out.println("校验失败");
        }else{
            System.out.println("校验成功");
        }

        String[] srcArray = specialTranslate(originArray);
        if (srcArray.length != originArray.length){
            System.out.println("存在转义");
        }
        isVerify = checkVerify(srcArray);
        if (!isVerify){
            System.out.println("校验失败");
        }else{
            System.out.println("校验成功");
        }
        System.out.println(Arrays.stream(srcArray).collect(Collectors.joining(" ")));
        System.out.println("==============================================================");
        if (BodyParser.PROTOCOL_HEADER.equals(srcArray[0]) && BodyParser.PROTOCOL_FOOTER.equals(srcArray[srcArray.length - 1])) {
            List<String> sb = new ArrayList<>();
            sb.add("包头(1,1)：" + srcArray[0]);
            sb.add("版本(2,3)：" + srcArray[1] + srcArray[2]);
            sb.add("目标地址(1,4)：" + srcArray[3]);
            sb.add("源地址(1,5)：" + srcArray[4]);
            sb.add("源流水号(1,6)：" + srcArray[5]);
            int bodyLength = Integer.parseInt(srcArray[6] + srcArray[7], 16);
            sb.add("长度(2,8)：" + srcArray[6] + srcArray[7] + "," + bodyLength);
            if (bodyLength > srcArray.length - BodyParser.PROTOCOL_HEADER_LENGTH) {
                sb.add("长度错误");
                System.out.println(String.join(" , ", sb));
                System.out.println("==============================================================");
                System.out.println();
                return ;
            }
            String[] body = Arrays.copyOfRange(srcArray, 8, 8 + bodyLength);
            int i = 0;
            while (i < body.length) {
                sb.add("子包类型：" + body[i] + "," + BodyParser.queryBodyTypeDescription(body[i]));
                int subBodyLength = Integer.parseInt(body[i + 1] + body[i + 2], 16);
                sb.add("子包长度：" + body[i + 1] + body[i + 2] + "," + subBodyLength);
                String[] subBody = Arrays.copyOfRange(body, i + 3, i + 3 + subBodyLength);
                if (subBodyLength == 0){
                    i +=3 ;
                    sb.add("包体长度为空") ;
                    continue;
                }
                sb.add("子包内容：" + String.join(" ", subBody));
                BodyParser bodyParser = BodyParserFactory.getInstance().getBodyParser(body[i]) ;
                bodyParser.setPrefix("  ");
                bodyParser.setSb(sb);
                bodyParser.parser(subBody);
                i += (3 + subBodyLength);
            }

            sb.add("\r\n");
            System.out.println(String.join("\r\n ", sb));
            System.out.println("==============================================================");
            System.out.println();
        } else {
            System.out.println("包体不全：" + item);
        }
    }

    private static Boolean checkVerify(String[] originArray) {

        int checkInteger = Integer.parseInt(originArray[0],16 ) ^  Integer.parseInt(originArray[1] ,16);
        for (int i = 2; i < originArray.length-2; i++) {
            checkInteger = checkInteger ^ Integer.parseInt(originArray[i],16 ) ;
        }
        return checkInteger == Integer.parseInt(originArray[originArray.length -2],16 ) ;
    }

    private static String[] specialTranslate(String[] originArray) {
        List<String> result = new ArrayList<>();
        int i = 0 ;
        while (i < originArray.length){
            if ("5E".equals(originArray[i])){
                Integer originChar = Integer.parseInt(originArray[i+1],16)^0x20 ;
                result.add(Integer.toHexString(originChar));
                i = i + 2 ;
            }else{
                result.add(originArray[i]);
                i = i + 1 ;
            }
        }
        return result.stream().toArray(String[]::new);
    }

}
