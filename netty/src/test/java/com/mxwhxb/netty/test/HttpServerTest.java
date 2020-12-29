package com.mxwhxb.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http服务端测试类
 *
 * @author Ma_wei
 * @since 2020/9/10 15:43
 */
public class HttpServerTest {

    public static void main(String[] args) {
        // 构建2个线程组
        EventLoopGroup bossExecutors = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //serverBootstrap.group(bossExecutors, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer())

        } catch (Exception e) {

        }
    }
}
