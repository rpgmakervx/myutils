package org.easyarch.myutils.http;

import org.junit.Test;

import java.net.URL;

/**
 * Description :
 * Created by xingtianyu on 16-12-30
 * 下午10:23
 * description:
 */

public class TestHttp {

    @Test
    public void testHttp() throws Exception {
//        System.out.println(InetAddress.getByName("www.baidu.com"));
        URL url = new URL("http://www.baidu.com/index.php");
        System.out.println(url.getHost());
        System.out.println(url.getPort());
//        AsyncHttpUtils utils = new AsyncHttpUtils("111.13.101.208",80);
//        boolean isDomain = utils.isDomain("127.0.0.1");
//        System.out.println(isDomain);
//        utils.connect();
//        utils.get("/index.php",null);
//        String content = new String(utils.getContentAsStream());
//        System.out.println(content);
    }
}
