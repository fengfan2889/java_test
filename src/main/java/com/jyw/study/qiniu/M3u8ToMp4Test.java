package com.jyw.study.qiniu;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class M3u8ToMp4Test {

    public static void main(String[] args) throws IOException {
        String m3u8Url = "https://bilibili.com-h-bilibili.com/20190412/8400_030d1cfc/1000k/hls/index.m3u8";
        String baseUrl = m3u8Url.substring(0, m3u8Url.lastIndexOf("/") + 1);
        HttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(m3u8Url);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //从响应中获取结果
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");

                String[] strs = result.split("\n");
                List<String> list = Stream.of(strs).filter(line -> !line.startsWith("#") && line.endsWith(".ts")).collect(Collectors.toList());
                System.out.println(JSONObject.toJSONString(list));
                int i = 0;
                for (String tsFilename : list) {
                    i = i + 1 ;
                    String url = baseUrl + tsFilename;
                    httpGet = new HttpGet(url);
                    response = httpClient.execute(httpGet);
                    InputStream inputStream = response.getEntity().getContent() ;
                    FileOutputStream outputStream = new FileOutputStream("d:\\test.mp4",true) ;
                    System.out.println(url + "     begin-------------" + i + "/" + list.size());
                    int byteCount = 0;
                    int bytesRead;
                    for(byte[] buffer = new byte[4096]; (bytesRead = inputStream.read(buffer)) != -1; byteCount += bytesRead) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println(url + "     end--------------------------");

                    outputStream.flush();
                    outputStream.close();

                }
            }
        }
    }
}