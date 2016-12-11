package org.easyarch.myutils.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.easyarch.myutils.http.future.ResponseFuture;
import org.easyarch.myutils.http.handler.BaseClientChildHandler;
import org.easyarch.myutils.http.manager.HttpResponseManager;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Description :
 * Created by xingtianyu on 16-12-8
 * 上午1:35
 */

public class NettyClient {

    private String ip;
    private int port;
    private EventLoopGroup workerGroup;
    private Bootstrap b;
    private ChannelFuture future;
    private Channel channel;

    public NettyClient(InetSocketAddress address) {
        try {
            this.ip = address.getHostString();
            this.port = address.getPort();
            workerGroup = new NioEventLoopGroup(32, Executors.newCachedThreadPool());
            b = new Bootstrap();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NettyClient(String ip, int port) {
        try {
            this.ip = ip;
            this.port = port;
            workerGroup = new NioEventLoopGroup();
            b = new Bootstrap();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        b.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(ip, port))
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new BaseClientChildHandler());
    }

    public void connect() {
        try {
            future = b.connect();
            future.sync();
            channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("channel id:" + channel.id());
    }

    public void send(FullHttpRequest request) throws Exception {
        System.out.println("future : " + future.isDone());
        HttpResponseManager.setAttr(channel,new ResponseFuture<FullHttpResponse>());
        write(request);
    }

    public void sendGet(String uri, HttpHeaders headers) throws Exception {
        DefaultFullHttpRequest request =
                new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri);
        setHeaders(request, headers);
        HttpResponseManager.setAttr(channel,new ResponseFuture<FullHttpResponse>());
        write(request);
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        getContent(responseFuture.get());
    }

    public void sendPost(String uri, HttpHeaders headers, byte[] bytes) throws Exception {
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.POST, uri, Unpooled.wrappedBuffer(bytes));
        HttpResponseManager.setAttr(channel,new ResponseFuture<FullHttpResponse>());
        setHeaders(request, headers);
        write(request);
    }

    private void setHeaders(FullHttpRequest request, HttpHeaders headers) {
        request.headers().set(headers);
    }

    private void write(FullHttpRequest request) throws Exception {
        channel.writeAndFlush(request);
        System.out.println("request has sent:" + future.channel().id());
    }

    public FullHttpResponse getWholeResponse() throws Exception {
        System.out.println("get whole response");
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        return responseFuture.get();
    }

    public byte[] getContentAsStream(){
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        try {
            return getContent(responseFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            close();
        }
    }

    public Map<String,Object> getHeadersAsMap(){
        ResponseFuture<FullHttpResponse> responseFuture =
                HttpResponseManager.getAttr(channel);
        Map<String,Object> headMap = new HashMap<>();
        try {
            HttpHeaders headers = getHeaders(responseFuture.get());
            for (Map.Entry<String,String> entry : headers.entries()){
                headMap.put(entry.getKey(),entry.getValue());
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
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        return bytes;
    }

    private HttpHeaders getHeaders(FullHttpResponse response){
        return response.headers();
    }
}
