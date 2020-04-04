package org.easyarch.myutils.concurrent.buffer;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-04-04
 * 本地缓冲器
 * 想象如下场景：
 * 我们从一个消息队列里获取消息，总是一个元素一个元素领取到worker线程的，
 * 如果下游处理服务也是一个元素处理一次，效率会比较低下，浪费过多的网络/磁盘io以及下游服务连接数
 * 这个时候服务侧使用一个本地队列是一个比较好的选择，先将请求聚合成一批然后统一发往下游批量处理，
 * 而这恰恰是缓冲区的功能
 * 本地缓冲区需要实现如下几个功能：
 * 1. 能够实现一个生产者消费者模型。提供生产的api，消费通过Consumer接口的方式提供用户
 * 2. 由于数据是放在内存的，所以需要有落地磁盘的机制（可选），启动时从本地加载未消费的数据避免丢失
 * 3. 消费限流，支持限流功能，该功能可以替换掉服务侧的限流组件功能，能够避免rpc请求队列堆积影响更多业务，结合
 * 功能2避免请求丢失。同时也提供一定的处理策略（丢弃，堆积，消息有效时长）
 * 4. 增加缓冲区监控api，能够实时观测到本地缓冲区堆积、处理速度等指标
 *
 */
public interface LocalBuffer<E extends Serializable>{

    void enqueue(E element);

    int length();

}
