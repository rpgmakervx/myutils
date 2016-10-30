package org.easyArch.myutils;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午6:58
 */

import org.easyArch.myutils.socket.io.SocketUtils;

import java.io.IOException;
import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午6:58
 */

public class Test {

    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("127.0.0.1",2000);
        Socket socket = SocketUtils.createCli("127.0.0.1",3000);
        String string = "hello world";
        SocketUtils.send(socket,string);
        System.out.println(SocketUtils.recvString(socket));
//        OutputStream os = socket.getOutputStream();
//        byte[] bytes;
//        if (string == null)
//            bytes = new byte[0];
//        else
//            bytes = string.getBytes();
//        try {
//            os.write(bytes,0,bytes.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            os.flush();
//        }

//        IOUtils.fill(socket.getOutputStream(), "hello world");
        System.out.println("11");
//        IOUtils.flushIO(socket.getOutputStream());
//        System.out.println(IOUtils.toString(socket.getInputStream()));
//        Reader is = new InputStreamReader(socket.getInputStream());
//        int len = 0;
//        char[] bytes = new char[1024];
//        StringBuffer buffer = new StringBuffer();
//        long count = 0;
//        try {
//            while ((len = is.read(bytes)) != -1) {
//                buffer.append(new String(bytes,0,len));
//                count += len;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(buffer.toString());
    }
}
