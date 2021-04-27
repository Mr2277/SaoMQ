package com.lls.remoting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lls.remoting.bean.MessageInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RemotingClient implements RemotingService {

    private Bootstrap bootstrap;

    private NioEventLoopGroup eventLoopGroup;

    private Channel target;

    public void init() {
        this.bootstrap = new Bootstrap();
        this.eventLoopGroup = new NioEventLoopGroup();
    }

    public void start() {
        bootstrap.group(eventLoopGroup)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new RemotingClientHandler());
                }
            });
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 1234).sync();
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("RemotingClient Startup!");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {

    }

    public void sendMessage(MessageInfo messageInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(messageInfo);
        byte[] bytes = jsonObject.toJSONString().getBytes();
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        target.writeAndFlush(byteBuf);
    }

    class RemotingClientHandler extends SimpleChannelInboundHandler {

        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            target = ctx.channel();
            System.out.println("Client--Active");
        }
    }
}
