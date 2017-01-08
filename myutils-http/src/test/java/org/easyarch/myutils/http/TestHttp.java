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
//        URL url = new URL("https://segmentfault.com/");
        AsyncHttpUtils utils = new AsyncHttpUtils();
        URL url = new URL("http://www.bilibili.com/");
        utils.get(url.toString(),null);
        String content = new String(utils.getContentAsStream());
        System.out.println(content);

    }

    @Test
    public void testHttpClient() throws IOException {
        HttpClient client = new DefaultHttpClient();
        // 实例化HTTP方法
        HttpGet request = new HttpGet("https://www.baidu.com");
        System.out.println("request:"+request);
        Header[] headers = request.getAllHeaders();
        System.out.println("headers:"+headers.length);
        for (Header h :headers){
            System.out.println(h.getName()+" : "+h.getValue());
        }
        System.out.println("....................");
        HttpResponse response = client.execute(request);
        InputStream is = response.getEntity().getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String NL = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer("");
        while ((line = in.readLine()) != null) {
            sb.append(line + "\n");
        }
        in.close();
        String content = sb.toString();
        System.out.println(content);
    }
}
