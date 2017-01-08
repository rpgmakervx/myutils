package org.easyarch.myutils.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.http.future.ResponseFuture;
import org.easyarch.myutils.http.handler.BaseClientChildHandler;
import org.easyarch.myutils.http.manager.HttpResponseManager;
import org.easyarch.myutils.lang.StringUtils;

import java.io.File;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 16-12-8
 * 上午1:35
 */

public class AsyncHttpUtils {

    private String ip;
    private int port;
    private EventLoopGroup workerGroup;
    private Bootstrap b;
    private ChannelFuture future;
    private Channel channel;

    private void init(URL url){
        try {
            init(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init(InetSocketAddress address){
        try {
            this.ip = address.getHostString();
            this.port = address.getPort();
            workerGroup = new NioEventLoopGroup();
            b = new Bootstrap();
            workerGroup = new NioEventLoopGroup();
            b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,256)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new BaseClientChildHandler());
            connect(InetAddress.getByName(ip).getHostAddress(),port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(String url) throws Exception {
        try {
            URL u = new URL(url);
            this.ip = InetAddress.getByName(u.getHost()).getHostAddress();
            this.port = u.getPort();
            if(port == -1){
                port = 80;
            }
            workerGroup = new NioEventLoopGroup();
            b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,256)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new BaseClientChildHandler());
            connect(ip,port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String ip() {
        return ip;
    }

    public int port() {
        return port;
    }

    private void connect(String ip,int port) {
        try {
            future = b.connect(ip,port);
            future.sync();
            channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String url, FullHttpRequest request) throws Exception {
        init(url);
        doRequest(new URL(url), request.method()
                , request.headers(), request.content());
    }

    public void get(String url, HttpHeaders headers) throws Exception {
        init(url);
        doRequest(new URL(url), HttpMethod.GET, headers, null);
    }

    public void post(String url, HttpHeaders headers, byte[] bytes) throws Exception {
        if (ArrayUtils.isEmpty(bytes)) {
            bytes = new byte[0];
        }
        init(url);
        doRequest(new URL(url), HttpMethod.POST, headers, Unpooled.wrappedBuffer(bytes));
    }

    public void put(String url, HttpHeaders headers, byte[] bytes) throws Exception {
        if (ArrayUtils.isEmpty(bytes)) {
            bytes = new byte[0];
        }
        init(url);
        doRequest(new URL(url), HttpMethod.PUT, headers, Unpooled.wrappedBuffer(bytes));
    }

    public void delete(String url, HttpHeaders headers) throws Exception {
        init(url);
        doRequest(new URL(url), HttpMethod.DELETE, headers, null);
    }

    private void doRequest(URL url, HttpMethod method, HttpHeaders headers, ByteBuf buf) {
        String uri = StringUtils.isEmpty(url.getPath())? File.separator:url.getPath();
        DefaultFullHttpRequest request;
        if(buf == null){
            request  = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, method, uri);
        }else{
            request  = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, method, uri, buf);
        }
        if (headers == null) {
            headers = new DefaultHttpHeaders();
            headers.set(HttpHeaderNames.HOST, url.getHost());
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        request.headers().set(headers);
//        request.headers().set(HttpHeaderNames.USER_AGENT, "java/HttpUtils");
        HttpResponseManager.setAttr(channel, new ResponseFuture<FullHttpResponse>());
        channel.writeAndFlush(request);
    }

    public FullHttpResponse getWholeResponse() throws Exception {
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        return responseFuture.get();
    }

    public byte[] getContentAsStream() {
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        try {
            FullHttpResponse response = responseFuture.get();
            System.out.println("response : "+response.content().readableBytes());
            return getContent(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public HttpHeaders getHeaders() {
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        try {
            return getHeaders(responseFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> getHeadersAsMap() {
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        Map<String, Object> headMap = new HashMap<>();
        try {
            HttpHeaders headers = getHeaders(responseFuture.get());
            for (Map.Entry<String, String> entry : headers.entries()) {
                headMap.put(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return headMap;
    }

    private void close() {
        workerGroup.shutdownGracefully();
    }

    private byte[] getContent(FullHttpResponse response) {
        ByteBuf buf = response.content();
        return ByteBufUtil.getBytes(buf);
    }

    private HttpHeaders getHeaders(FullHttpResponse response) {
        return response == null ? null : response.headers();
    }


}
