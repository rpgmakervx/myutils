package org.easyarch.myutils.http.util;

import io.netty.buffer.ByteBuf;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午2:54
 * description:
 */

public class ByteBufUtil {

    public static byte[] buf2Bytes(ByteBuf buf) {
        int readable = buf.readableBytes();
        int readerIndex = buf.readerIndex();
        if (buf.hasArray()) {
            byte[] array = buf.array();
            if (buf.arrayOffset() == 0 && readerIndex == 0 && array.length == readable) {
                return array;
            }
        }
        byte[] array = new byte[readable];
        buf.getBytes(readerIndex, array);
        return array;
    }
}


