package org.easyArch.myutils.socket;/**
 * Description : 
 * Created by YangZH on 16-10-30
 *  下午5:50
 */

import java.net.Socket;

/**
 * Description :
 * Created by code4j on 16-10-30
 * 下午5:50
 */

public interface SocketListener {

    public void read(String msg);
    public void read(byte[] bytes);
    public void active(Socket socket);
}
