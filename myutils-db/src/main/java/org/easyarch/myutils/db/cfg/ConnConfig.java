package org.easyarch.myutils.db.cfg;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午7:28
 */

import java.util.Properties;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午7:28
 */

public class ConnConfig {

    private static String user;
    private static String password;
    private static String url;
    private static String drivername;


    public static void config(String user,String password,
                              String url,String drivername){
        ConnConfig.user = user;
        ConnConfig.password = password;
        ConnConfig.url = url;
        ConnConfig.drivername = drivername;
        registerDriver();
    }

    public static void config(Properties props){
        config(props.getProperty("user"),
                props.getProperty("password"),
                props.getProperty("url"),
                props.getProperty("drivername"));
        registerDriver();
    }
//    public static void config(String user,String password,
//                              String url,String drivername,
//                              int minConnection,int maxConnection,
//                              int minIdle,int maxWait){
//        ConnectInfo.user = user;
//        ConnectInfo.password = password;
//        ConnectInfo.url = url;
//        ConnectInfo.drivername = drivername;
//        ConnectInfo.minConnection = minConnection;
//        ConnectInfo.maxConnection = maxConnection;
//        ConnectInfo.minIdle = minIdle;
//        ConnectInfo.maxWait = maxWait;
//        registerDriver();
//    }
//
//    public static void config(Properties props){
//        user = props.getProperty("user");
//        password = props.getProperty("password");
//        url = props.getProperty("url");
//        drivername = props.getProperty("drivername");
//        ConnectInfo.minConnection = Integer.parseInt(props.getProperty("drivername"));
//        ConnectInfo.maxConnection = Integer.parseInt(props.getProperty("drivername"));
//        ConnectInfo.minIdle = Integer.parseInt(props.getProperty("drivername"));
//        ConnectInfo.maxWait = Integer.parseInt(props.getProperty("drivername"));
//        registerDriver();
//    }

    private static void registerDriver(){
        try {
            Class.forName(drivername);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUser() {
        return user;
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
