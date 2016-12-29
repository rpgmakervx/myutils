package org.easyarch.myutils.test.codec;

import org.easyarch.myutils.codec.CodecUtils;
import org.junit.Test;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 下午10:34
 * description:
 */

public class TestCodec {

    @Test
    public void testHash(){
        System.out.println(CodecUtils.sha1("hello world"));
        System.out.println(CodecUtils.md5("hello world"));
        System.out.println(CodecUtils.sha1("hello world".getBytes()));
        System.out.println(CodecUtils.md5("hello world".getBytes()));
        System.out.println(CodecUtils.md5("hello world".getBytes()));
        System.out.println(CodecUtils.hash("hello world"));
        System.out.println(CodecUtils.encodeBase64("hello world".getBytes()));
        System.out.println(new String(CodecUtils.decodeBase64(CodecUtils.encodeBase64("hello world".getBytes()))));
        System.out.println(CodecUtils.hmacSHA1("hello world".getBytes(),"9ybGQ=").length);

    }
}
