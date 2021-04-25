package com.netty.summary.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf s) throws Exception {
        System.out.println("MyServer--channelRead0");
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("MyServer--channelRead");
        super.channelRead(channelHandlerContext, msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyServer--handlerAdded");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端上线通知
        System.out.println("MyServer--channelActive");
        ByteBuf byteBuf = Unpooled.copiedBuffer("dfsdfs".getBytes());
        ctx.channel().writeAndFlush(byteBuf);
    }

}
