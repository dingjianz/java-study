package com.itheima.InetAddressDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendMessageDemo2 {
    /*
    UDP 组播发送端代码
     */
    public static void main(String[] args) throws IOException {
        // 创建 MulticastSocket 对象
        MulticastSocket ms = new MulticastSocket();

        // 创建 DatagramPacket 对象
        String msg = "hello multicast";
        byte[] bytes = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.0.1"), 8888);

        // 调用 MulticastSocket 对象的 send 方法发送数据
        ms.send(dp);

        // 调用 MulticastSocket 对象的 close 方法释放资源
        ms.close();
    }
}
