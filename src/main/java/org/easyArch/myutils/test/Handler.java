package org.easyArch.myutils.test;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午10:31
 */

import java.io.*;
import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午10:31
 */

public class Handler implements Runnable {

    private Socket socket;

    public Handler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            PrintStream ps = new PrintStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println("收到客户端信息：" + br.readLine());
            ps.print("hi! welcome to this server");
            ps.flush();
            ps.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
