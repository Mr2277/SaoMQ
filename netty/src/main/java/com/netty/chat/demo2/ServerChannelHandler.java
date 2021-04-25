package com.netty.chat.demo2;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


public class ServerChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {


    List<Channel> channels = new ArrayList<>();


    /**
     * 监听客户端注册到服务器上
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("一个客户端已连接");
        channels.add(ctx.channel());
    }

    //消息处理的方法
    @Override
    protected void channelRead0(ChannelHandlerContext chc, ByteBuf byteBuf) throws Exception {
        //System.out.println("接收到客户端的消息" + byteBuf.toString());
        System.out.println("channelRead0");
        /*
        //将消息群发给其他客户端  unpooled 未共享
        for (Channel channel : channels) {
            if (channel != chc.channel()){//发信息给所有非当前客户端
                ByteBuf buf = Unpooled.copiedBuffer(byteBuf);
                channel.writeAndFlush(buf);
            }
        }
        */
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        super.channelRead(ctx, msg);
    }

}
