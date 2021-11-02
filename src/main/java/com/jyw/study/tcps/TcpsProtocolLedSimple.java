package com.jyw.study.tcps;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 一个led的解析
 * @author jyw
 */
public class TcpsProtocolLedSimple {

    private static List<String> readFileContent() {

        //File file = new File("D:\\jyw\\chat\\weixin\\WeChat Files\\wxid_tb4tykzzqdir51\\FileStorage\\File\\2021-10\\20210703.log");
        File file = new File("D:\\users\\jyw\\Desktop\\temp\\3.txt");
        BufferedReader reader = null;
        List<String> resultList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.startsWith("BB")){
                    resultList.add(tempStr);
                }
            }
            reader.close();
            return resultList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

        List<String> bodys = readFileContent();
        if (bodys != null) {
            bodys.forEach(TcpsProtocolLedSimple::accept);
        }
    }

    private static Map<String,String> bodyTypeMap = new HashMap<String, String>(){
        {

        }

    };

    private static String queryBodyTypeDescription(String bodyType){
        return null ;
    }

    private static void accept(String item) {
        String[] srcArray = item.replaceAll(" {2}", "").split(" ");
        System.out.println(item);
        if ("BB".equals(srcArray[0]) && "55".equals(srcArray[srcArray.length - 1])) {
            List<String> sb = new ArrayList<>();
            sb.add("包头：" + srcArray[0]);
            sb.add("版本：" + srcArray[1] + srcArray[2]);
            sb.add("目标地址：" + srcArray[3]);
            sb.add("源地址：" + srcArray[4]);
            sb.add("源流水号：" + srcArray[5]);
            int bodyLength = Integer.parseInt(srcArray[6] + srcArray[7], 16);
            sb.add("长度：" + srcArray[6] + srcArray[7] + "," + bodyLength);
            if (bodyLength > srcArray.length - 10) {
                sb.add("长度错误");
                System.out.println("==============================================================");

                System.out.println(String.join(" , ", sb));
                System.out.println("==============================================================");
                System.out.println();
                return ;
            }
            String[] body = Arrays.copyOfRange(srcArray, 8, 8 + bodyLength);
            int i = 0;
            while (i < body.length) {
                sb.add("子包类型：" + body[i]);
                int subBodyLength = Integer.parseInt(body[i + 1] + body[i + 2], 16);
                sb.add("子包长度：" + body[i + 1] + body[i + 2] + "," + subBodyLength);
                String[] subBody = Arrays.copyOfRange(body, i + 3, i + 3 + subBodyLength);
                sb.add("子包内容：" + String.join("", subBody));
                Byte[] srcByteArray = Arrays.stream(subBody).map(s -> Integer.valueOf(s, 16).byteValue()).toArray(Byte[]::new);
                byte[] ss = new byte[srcByteArray.length];
                IntStream.range(0, srcByteArray.length).forEach(j -> ss[j] = srcByteArray[j]);
                sb.add("解析内容：" + (new String(ss, StandardCharsets.UTF_8)));
                i += (3 + subBodyLength);
            }
            sb.add("\r\n");

            System.out.println("==============================================================");

            System.out.println(String.join(" \r\n ", sb));
            System.out.println("==============================================================");
            System.out.println();
        } else {
            System.out.println("包体不全：" + item);
        }
    }
}
