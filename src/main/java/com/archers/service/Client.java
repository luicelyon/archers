package main.java.com.archers.service;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by hello on 16-8-5.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String ServerIpAddress = "127.0.0.1";// 对方的IP
        String words = "Hello";// 对方的话
        String temp = null;
        byte[] serverSay = new byte[1024];// 读取<1KB
        InputStreamReader read = null;
        int len = 0;
        Socket s = null;
        OutputStream outputstream = null;
        InputStream inputstream = null;
        // 取得你给他的IP
        System.out.println("请输入对方的IP(默认发给自己):");
        read = new InputStreamReader(System.in);
        try {
            temp = new BufferedReader(read).readLine();
            if (!temp.equals(""))
                ServerIpAddress = temp;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 取得你各他的Message
        System.out.println("请输入想对他(她)说的话----输入QUIT 退出!!:");
        read = new InputStreamReader(System.in);
        while (true) {
            try {
                temp = "Hello";
                temp = new BufferedReader(read).readLine();
                words = temp;
                if (words.equals("quit"))
                    break;
                temp = "客户端....." + words;
                // 发起通信
                s = new Socket(InetAddress.getByName(ServerIpAddress), 9527);// 套接字的IP地址和端口号
                outputstream = s.getOutputStream();
                inputstream = s.getInputStream();
                outputstream.write(temp.getBytes());// 向服务器发送消息
                len = inputstream.read(serverSay);// 接受服务器消息
                System.out.println( new String(serverSay, 0, len));// 客户端控制台显示服务器返回的信息
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                inputstream.close();
                outputstream.close();
                s.close();// 记住一定要关闭这些输入，输出流和套接字
            }
        }// end while
    }
}
