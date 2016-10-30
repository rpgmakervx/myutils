package org.easyArch.myutils.test;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午10:37
 */

import java.io.*;
import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午10:37
 */

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",3000);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print("hello!!");
            ps.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
