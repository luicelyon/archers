package main.java.com.archers.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hello on 16-8-5.
 */
public class Server {

    public static void main(String args[]) throws IOException {
        String reces = null;
        int len;
        Socket socket = null;
        OutputStream outputstream = null;
        InputStream inputstream = null;
        byte[] rece = new byte[1024];
        try {
            ServerSocket serversocket = new ServerSocket(9527);
            while (true) {
                socket = serversocket.accept();
                inputstream = socket.getInputStream();
                outputstream = socket.getOutputStream();
                len = inputstream.read(rece);
                if (len != 0){
                    reces = new String(rece, 0, len);
                }
                System.out.println(reces);
                BufferedReader bufferreader = new BufferedReader(
                        new InputStreamReader(System.in));
                outputstream.write(("服务器....."+bufferreader.readLine()).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputstream.close();
            outputstream.close();
            socket.close();
        }

    }
}
