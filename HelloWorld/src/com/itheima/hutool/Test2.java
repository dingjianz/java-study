package com.itheima.hutool;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test2 {
    /*
      制造假数据:
        获取姓氏: https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d
        获取男生名字: http://www.haoming8.cn/baobao/10881.html
        获取女生名字: http://www.haoming8.cn/baobao/7641.html
     */

    public static void main(String[] args) throws IOException {
        String url1 = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d";
        String s = webCrawler(url1);
        System.out.println(s);
    }

    /**
     * 网络爬虫
     *
     * @param url 网址
     */
    public static String webCrawler(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL _URL = new URL(url);
        URLConnection urlConnection = _URL.openConnection();
        InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }
        isr.close();
        return sb.toString();
    }
}
