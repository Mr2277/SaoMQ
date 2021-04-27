package com.lls.nameserver.bean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class ProducerHandler extends SimpleChannelInboundHandler {

    private List<Channel> channelList = new ArrayList<Channel>();

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Producer--Active");
        //super.channelActive(ctx);
    }
}
