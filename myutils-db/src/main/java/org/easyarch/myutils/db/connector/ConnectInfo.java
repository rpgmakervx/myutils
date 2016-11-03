package org.easyarch.myutils.db.connector;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午7:28
 */

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午7:28
 */

public class ConnectInfo {

    private static String username;
    private static String password;
    private static String url;
    private static String drivername;

    public static void config(String username,String password,
                              String url,String drivername){
        ConnectInfo.username = username;
        ConnectInfo.password = password;
        ConnectInfo.url = url;
        ConnectInfo.drivername = drivername;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDrivername() {
        return drivername;
    }
}
