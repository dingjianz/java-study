package com.itheima.InetAddressDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        /*
        UDP 通信程序 - 接收数据
         */

        // 1.创建DatagramSocket对象
        // 细节：在接收的时候，一定要绑定端口
        // 而且绑定的端口一定要跟发送的端口保持一致
        DatagramSocket ds= new DatagramSocket(8888);

        // 2.接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

        // 该方法是阻塞的，程序执行到这一步的时候，会在这里等待，直到收到数据包
        ds.receive(dp);

        // 3.解析数据包
        byte[] data = dp.getData();
        int len = dp.getLength();
        int port = dp.getPort();
        InetAddress address = dp.getAddress();

        System.out.println(new String(data, 0, len));
        System.out.println("端口："+port);
        System.out.println("地址："+address);

        // 4.释放资源
        ds.close();
    }
}
