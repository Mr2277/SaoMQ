package com.netty.summary.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        bootstrap.group(loopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new MyServerHandler());
                    }
                });
        ChannelFuture f = bootstrap.bind(1234).sync();
        f.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("MyServer Success!");
                } else {
                    System.out.println("MyServer Failed!");
                }
            }
        });
    }
}
