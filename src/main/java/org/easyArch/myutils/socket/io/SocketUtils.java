package org.easyArch.myutils.socket.io;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午5:39
 */

import org.easyArch.myutils.io.IOUtils;
import org.easyArch.myutils.socket.SocketListener;

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
                return IOUtils.toByteArray(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String recvString(Socket socket){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                return IOUtils.toString(socket.getInputStream());
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
                IOUtils.fill(socket.getOutputStream(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(Socket socket,String data){
        try {
            if (!socket.isClosed()&&socket.isConnected())
                IOUtils.fill(socket.getOutputStream(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = SocketUtils.createServ(3000);
        System.out.println("服务开启");
        while (true){
            System.out.println("等待连接");
//            Socket socket = ss.accept();
            Socket socket = SocketUtils.wait(ss);
            System.out.println("新连接加入");
            System.out.println(SocketUtils.recvString(socket));
            SocketUtils.send(socket,"hi! I know you!");
            closeSocket(socket);

//            String request = IOUtils.toString(socket.getInputStream());
//            System.out.println(request);
//            Reader is = new InputStreamReader(socket.getInputStream());
//            int len = 0;
//            char[] bytes = new char[1024];
//            StringBuffer buffer = new StringBuffer();
//            long count = 0;
//            try {
//                while ((len = is.read(bytes)) != -1) {
//                    buffer.append(new String(bytes,0,len));
//                    count += len;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String string = "hi! nice to meet you!";
//            OutputStream os = socket.getOutputStream();
//            byte[] bytes;
//            if (string == null)
//                bytes = new byte[0];
//            else
//                bytes = string.getBytes();
//            try {
//                os.write(bytes,0,bytes.length);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                os.flush();
//            }
//            os.close();
//            IOUtils.fill(socket.getOutputStream(), "hi! nice to meet you!");
//            IOUtils.closeIO(socket.getOutputStream());

        }
    }
}
