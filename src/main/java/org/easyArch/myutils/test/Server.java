package org.easyArch.myutils.test;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午10:28
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午10:28
 */

public class Server{

    private ServerSocket ss;

    public Server(int port){
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void init(){
        while (true){
            System.out.println("等待请求");
            try {
                Socket socket = ss.accept();
                System.out.println("收到一个请求");
                new Thread(new Handler(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
