package org.easyarch.myutils.jdbc.cfg;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午7:28
 */

import org.easyarch.myutils.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
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

    public static void config(String path){
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            config(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeIO(fis);
        }
    }

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
