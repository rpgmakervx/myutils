package org.easyArch.myutils.socket.io;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午5:39
 */

import org.easyArch.myutils.io.IOUtils;
import org.easyArch.myutils.socket.SocketListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午5:39
 */

public class SocketUtils {

    public static void closeSocket(Socket socket){
        try {
            if (socket == null||socket.isClosed())
                return;
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeSocket(ServerSocket ss){
        try {
            if (ss == null||ss.isClosed())
                return;
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------Server----------------------

    public static ServerSocket createServ(int port){
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Socket wait(ServerSocket ss){
        try {
            return ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void listen(SocketListener listener){
    }

    public static byte[] recvByte(Socket socket){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                return IOUtils.toByteArrayx(socket.getInputStream()
                        , socket.getInputStream().available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String recvString(Socket socket){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                return IOUtils.toStringx(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //---------------------Client------------------------

    public static Socket createCli(String ip,int port){
        try {
            return new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void send(Socket socket,byte[] data){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                IOUtils.write(socket.getOutputStream(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(Socket socket,String data){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                IOUtils.write(socket.getOutputStream(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        ServerSocket ss = SocketUtils.createServ(3000);
//        String respMsg = "hi welcome to server";
//        Socket socket = SocketUtils.wait(ss);
//        System.out.println("收到客户端消息："+SocketUtils.recvString(socket));
//        SocketUtils.send(socket,respMsg);

//        ByteBuffer buf = ByteBuffer.allocate(10);
//        buf.put((byte) 1);
//        buf.put((byte) 2);
//        buf.put((byte) 3);
//        buf.put((byte) 4);
//        buf.flip();
//
//        for (int index=0;index<buf.limit();index++){
//            System.out.println(buf.get());
//        }
//        System.out.println((byte)'[');
        long length = IOUtils.nioCopyln(new FileInputStream("/home/code4j/testdirectory/test/data.json"),
                new FileOutputStream("/home/code4j/testdirectory/test/json.data"));
        System.out.println("long --> "+length);
    }

}
