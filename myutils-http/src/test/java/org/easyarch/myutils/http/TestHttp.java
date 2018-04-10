package org.easyarch.myutils.http;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
//        URL url = new URL("https://segmentfault.com");
//        URI uri = new URI("https://segmentfault.com/");
//        System.out.println(url.getPath());
//        System.out.println(uri.getHost());
        AsyncHttpUtils utils = new AsyncHttpUtils();
//        URL url = new URL("http://localhost:9524/");
        URL url = new URL("http://10.37.17.3:30147/all/capacity");

        long begin = System.currentTimeMillis();
        utils.get(url.toString(),null);
        System.out.println("cost:"+(System.currentTimeMillis() - begin));
        String contents = new String(utils.getContentAsStream());

    }

    @Test
    public void testHttpClient() throws IOException {
        HttpClient client = new DefaultHttpClient();
        // 实例化HTTP方法
        HttpGet request = new HttpGet("http://10.37.17.3:30147/all/capacity");
        System.out.println("request:"+request);
        Header[] headers = request.getAllHeaders();
        System.out.println("headers:"+headers.length);
        System.out.println("....................");
        long begin = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        System.out.println("cost:"+(System.currentTimeMillis() - begin));
        InputStream is = response.getEntity().getContent();
    }
}
