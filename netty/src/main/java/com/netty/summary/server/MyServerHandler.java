package com.netty.summary.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        System.out.println("MyServer--" + msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyServer--handlerAdded");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端上线通知
        System.out.println("ServerAc");
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress()+"在线");
        ByteBuf byteBuf = Unpooled.copiedBuffer("dfsdfs".getBytes());
        //tx.channel().writeAndFlush(byteBuf);
    }
}
