package com.lls.remoting;

import com.alibaba.fastjson.JSON;
import com.lls.remoting.bean.MessageInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;

public class RemotingServer implements RemotingService {

    private ServerBootstrap serverBootstrap;

    private NioEventLoopGroup eventLoopGroup;

    private Integer port = 1234;

    private List<MessageInfo> messageInfos = new ArrayList<>();

    public void init() {
        this.serverBootstrap = new ServerBootstrap();
        this.eventLoopGroup = new NioEventLoopGroup();
    }

    public void start() {
        serverBootstrap.group(eventLoopGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new StringEncoder());
                    socketChannel.pipeline().addLast(new RemotingServerHandler());
                }
            });
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("Remote Server Startup!");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {

    }

    class RemotingServerHandler extends SimpleChannelInboundHandler {

        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
            ByteBuf byteBuf = (ByteBuf) o;
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String json = new String(bytes);
            System.out.println(json);
            //MessageInfo messageInfo = JSON.parseObject(json, MessageInfo.class);
            //messageInfos.add(messageInfo);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Server--Active");
        }


    }
}
