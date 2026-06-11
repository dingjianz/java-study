package com.itheima.InetAddressDemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test1 {
    /*
        获取本机的InetAddress对象

           static InetAddress getByName(String host) 获取主机名(或者主机ip)对应的InetAddress对象
           String getHostName() 获取此IP地址的主机名
           String getHostAddress() 返回文本显示中的 IP 地址字符串
     */

   public static void main(String[] args) throws UnknownHostException {
       // 1. 获取本机的InetAddress对象
       InetAddress address = InetAddress.getByName("192.168.0.1");
       System.out.println(address);

       System.out.println(address.getHostName());

       System.out.println(address.getHostAddress());
   }
}
