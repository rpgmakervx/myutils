package org.easyArch.myutils.test;/**
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
        Socket socket = SocketUtils.createCli("127.0.0.1",3000);
        SocketUtils.send(socket,"hello server!");
        System.out.println("收到服务端消息："+SocketUtils.recvString(socket));
    }
}
